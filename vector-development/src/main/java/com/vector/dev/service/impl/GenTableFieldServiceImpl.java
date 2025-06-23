package com.vector.dev.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vector.dev.entity.GenTableField;
import com.vector.dev.mapper.GenTableFieldMapper;
import com.vector.dev.service.GenTableFieldService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wengxs
 */
@Service
public class GenTableFieldServiceImpl extends ServiceImpl<GenTableFieldMapper, GenTableField>
        implements GenTableFieldService {

    @Override
    public List<GenTableField> selectFromInformationSchema(String dbName, String tableName) {
        return baseMapper.selectFromInformationSchema(dbName, tableName);
    }
}
