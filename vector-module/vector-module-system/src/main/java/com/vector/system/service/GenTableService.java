package com.vector.system.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vector.system.entity.GenTable;

import java.util.List;
import java.util.Map;

/**
 * Created by Zorg
 * 2020/6/9 19:38
 */
public interface GenTableService extends IService<GenTable> {

    Map<String, String> preview(Long id);

    void removeAllByIds(List<Long> ids);

    List<String> listDatabase();

    IPage<GenTable> pageSchema(IPage<GenTable> page, String dbName);

    void importSchema(String dbName, String tableName);
}
