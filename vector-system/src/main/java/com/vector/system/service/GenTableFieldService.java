package com.vector.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vector.system.pojo.entity.GenTableField;

import java.util.List;

/**
 * @author wengxs
 */
public interface GenTableFieldService extends IService<GenTableField> {

    List<GenTableField> selectFromInformationSchema(String dbName, String tableName);
}
