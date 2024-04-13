package com.vector.system.dto;

import com.vector.system.entity.SysRole;
import com.vector.system.entity.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zorg
 * 2020/6/15 00:01
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysUserDto extends SysUser {

    private Long[] roleIds;

    List<SysRole> roles = new ArrayList<>();
}
