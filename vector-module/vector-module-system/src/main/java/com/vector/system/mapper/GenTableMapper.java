package com.vector.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vector.system.entity.GenTable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Zorg
 * 2020-03-20 11:48
 */
@Mapper
public interface GenTableMapper extends BaseMapper<GenTable> {

    @Select("show databases")
    List<String> showDatabases();

    @Select("select table_schema dbName, table_name tbName, table_comment tbComment, create_time createTime, update_time updateTime " +
            "from information_schema.tables " +
            "where table_schema = #{dbName} " + // (select database())
//            "AND table_name NOT LIKE 'gen_%' " +
            "AND CONCAT(table_schema,'.',table_name) NOT IN (select CONCAT(db_name,'.',tb_name) from gen_table)")
    IPage<GenTable> selectFromInformationSchema(IPage<GenTable> page, @Param("dbName") String dbName);

    @Select("select table_schema dbName, table_name tbName, table_comment tbComment, create_time, update_time " +
            "from information_schema.tables " +
            "where table_schema = #{dbName} " +
            "AND table_name = #{tableName}")
    GenTable selectFromInformationSchemaByTableName(@Param("dbName") String dbName, @Param("tableName") String tableName);
}
