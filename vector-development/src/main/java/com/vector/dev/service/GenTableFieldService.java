package com.vector.dev.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vector.dev.entity.GenTableField;

import java.util.List;

/**
 * @author wengxs
 */
public interface GenTableFieldService extends IService<GenTableField> {

    List<GenTableField> selectFromInformationSchema(String dbName, String tableName);
}
