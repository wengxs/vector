package com.vector.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vector.system.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Zorg
 * 2020/5/16 14:17
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    @Select("SELECT DISTINCT a.* FROM sys_role a LEFT JOIN sys_user_role b ON b.role_id=a.id WHERE b.user_id=#{userId}")
    List<SysRole> selectAllByUserId(Long userId);

    @Select("select * from sys_role where name=#{name}")
    SysRole selectOneByName(String name);
}
