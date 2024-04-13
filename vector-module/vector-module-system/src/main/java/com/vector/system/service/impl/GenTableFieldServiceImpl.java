package com.vector.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vector.system.entity.GenTableField;
import com.vector.system.mapper.GenTableFieldMapper;
import com.vector.system.service.GenTableFieldService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Zorg
 * 2020/6/9 19:39
 */
@Service
public class GenTableFieldServiceImpl extends ServiceImpl<GenTableFieldMapper, GenTableField>
        implements GenTableFieldService {

    @Override
    public List<GenTableField> selectFromInformationSchema(String dbName, String tableName) {
        return baseMapper.selectFromInformationSchema(dbName, tableName);
    }
}
