package com.vector.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vector.system.entity.GenTableField;

import java.util.List;

/**
 * Created by Zorg
 * 2020/6/9 19:39
 */
public interface GenTableFieldService extends IService<GenTableField> {

    List<GenTableField> selectFromInformationSchema(String dbName, String tableName);
}
