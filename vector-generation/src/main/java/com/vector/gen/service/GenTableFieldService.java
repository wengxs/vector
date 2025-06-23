package com.vector.gen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vector.gen.entity.GenTableField;

import java.util.List;

/**
 * @author wengxs
 */
public interface GenTableFieldService extends IService<GenTableField> {

    List<GenTableField> selectFromInformationSchema(String dbName, String tableName);
}
