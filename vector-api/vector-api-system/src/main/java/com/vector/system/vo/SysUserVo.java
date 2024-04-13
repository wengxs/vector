package com.vector.system.vo;

import com.vector.system.entity.SysRole;
import com.vector.system.entity.SysUser;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zorg
 * 2020/6/6 23:33
 */
@Data
public class SysUserVo extends SysUser {

    List<SysRole> roles = new ArrayList<>();
}
