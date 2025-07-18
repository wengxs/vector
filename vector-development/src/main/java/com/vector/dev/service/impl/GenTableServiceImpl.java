package com.vector.dev.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vector.common.security.util.SecurityUtils;
import com.vector.dev.constant.GenConstant;
import com.vector.dev.entity.GenTable;
import com.vector.dev.entity.GenTableField;
import com.vector.dev.mapper.GenTableMapper;
import com.vector.dev.service.GenTableFieldService;
import com.vector.dev.service.GenTableService;
import com.vector.dev.util.VelocityUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.StringWriter;
import java.util.*;

/**
 * @author wengxs
 */
@Service
public class GenTableServiceImpl extends ServiceImpl<GenTableMapper, GenTable> implements GenTableService {

    @Autowired
    private GenTableFieldService genTableFieldService;

    @Override
    public Map<String, String> preview(Long id) {
        Map<String, String> dataMap = new LinkedHashMap<>();
        GenTable table = baseMapper.selectById(id);

        List<GenTableField> tableFields = genTableFieldService.list(new LambdaQueryWrapper<GenTableField>()
                .eq(GenTableField::getTableId, id)
                .orderByAsc(GenTableField::getSort)
        );

        VelocityUtil.initVelocity();
        VelocityContext context = VelocityUtil.prepareContext(table, tableFields);
        // 获取模板列表
        List<String> templates = VelocityUtil.getTemplateList();
        for (String template : templates) {
            // 渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, "UTF-8");
            tpl.merge(context, sw);
            dataMap.put(template, sw.toString());
        }
        return dataMap;
    }

    private void fillTableFieldProperty(GenTableField tableField) {
        tableField.setJavaField(StringUtils.uncapitalize(convertToCamelCase(tableField.getName())));
        String fieldType = getFieldType(tableField.getType());
        // 设置字段java类型
        if (containsType(GenConstant.FIELD_TYPE_STRING, fieldType)) {
            tableField.setJavaType(GenConstant.JAVA_TYPE_STRING);
        } else if (containsType(GenConstant.FIELD_TYPE_TIME, fieldType)) {
            tableField.setJavaType(GenConstant.JAVA_TYPE_DATE);
        } else if (containsType(GenConstant.FIELD_TYPE_LONG, fieldType)) {
            tableField.setJavaType(GenConstant.JAVA_TYPE_LONG);
        } else if (containsType(GenConstant.FIELD_TYPE_INTEGER, fieldType)) {
            tableField.setJavaType(GenConstant.JAVA_TYPE_INTEGER);
        } else if (containsType(GenConstant.FIELD_TYPE_DECIMAL, fieldType)) {
            tableField.setJavaType(GenConstant.JAVA_TYPE_BIG_DECIMAL);
        } else if (containsType(GenConstant.FIELD_TYPE_DOUBLE, fieldType)) {
            tableField.setJavaType(GenConstant.JAVA_TYPE_DOUBLE);
        } else if (containsType(GenConstant.FIELD_TYPE_BOOLEAN, fieldType)) {
            tableField.setJavaType(GenConstant.JAVA_TYPE_BOOLEAN);
        } else if (containsType(GenConstant.FIELD_TYPE_JSON, fieldType)) {
            tableField.setJavaType(GenConstant.JAVA_TYPE_JSON);
        } else {
            tableField.setJavaType(GenConstant.JAVA_TYPE_STRING);
        }
        Set<String> fieidSet = new HashSet<>();
        Collections.addAll(fieidSet, "id", "create_time", "create_by", "update_time", "update_by");
        if (fieidSet.contains(tableField.getName())) {
            return;
        }
        // 默认生成信息
        tableField.setIsForm(true);
        tableField.setFormComponent("INPUT");
        tableField.setIsList(true);
        tableField.setIsQuery(true);
        tableField.setQueryType("EQ");
        tableField.setQueryComponent("INPUT");
    }

    private boolean containsType(String[] fieldTypes, String fieldType) {
        return Arrays.asList(fieldTypes).contains(fieldType);
    }

    private String getFieldType(String fieldType) {
        if (StringUtils.indexOf(fieldType, "(") > 0) {
            return StringUtils.substringBefore(fieldType, "(");
        } else {
            return fieldType;
        }
    }

    private void fillTableProperty(GenTable table) {
        String tbName = table.getTbName();
        int index = tbName.indexOf("_");
        String moduleName = table.getDbName();
        String bizPrefix = tbName.substring(0, index);
        String bizName = tbName.substring(index + 1);
        table.setClassName(convertToCamelCase(tbName));
        table.setPackageName(GenConstant.DEFAULT_PACKAGE_PREFIX + moduleName);
        table.setModuleName(moduleName);
        table.setBizPrefix(bizPrefix);
        table.setBizName(convertToCamelCase(bizName));
        table.setAuthor(SecurityUtils.getUsername());
    }

    private String convertToCamelCase(String str) {
        if (str == null || str.isEmpty())
            return "";
        String[] strArr = str.split("_");
        StringBuilder sb = new StringBuilder();
        for (String s : strArr) {
            sb.append(StringUtils.capitalize(s));
        }
        return sb.toString();
    }

    @Override
    @Transactional
    public void removeAllByIds(List<Long> ids) {
        genTableFieldService.remove(new LambdaQueryWrapper<GenTableField>().in(GenTableField::getTableId, ids));
        baseMapper.deleteBatchIds(ids);
    }

    @Override
    public List<String> listDatabase() {
        return baseMapper.showDatabases();
    }

    @Override
    public IPage<GenTable> pageSchema(IPage<GenTable> page, String dbName) {
        return baseMapper.selectFromInformationSchema(page, dbName);
    }

    @Override
    @Transactional
    public void importSchema(String dbName, String tableName) {
        GenTable table = baseMapper.selectFromInformationSchemaByTableName(dbName, tableName);
        fillTableProperty(table);
        baseMapper.insert(table);
        List<GenTableField> tableFields = genTableFieldService.selectFromInformationSchema(dbName, tableName);
        for (GenTableField tableField : tableFields) {
            tableField.setTableId(table.getId());
            tableField.setIsForm(false);
            tableField.setIsList(false);
            tableField.setIsQuery(false);
            fillTableFieldProperty(tableField);
            genTableFieldService.save(tableField);
        }
    }

    @Override
    @Transactional
    public void updateAll(GenTable genTable) {
        baseMapper.updateById(genTable);
        List<GenTableField> genTableFields = genTable.getFields();
        for (GenTableField genTableField : genTableFields) {
            genTableField.setTableId(genTable.getId());
            genTableFieldService.updateById(genTableField);
        }
    }
}
