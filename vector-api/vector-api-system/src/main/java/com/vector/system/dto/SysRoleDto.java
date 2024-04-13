package com.vector.system.dto;

import com.vector.system.entity.SysRole;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zorg
 * 2020/6/15 00:06
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysRoleDto extends SysRole {

    private Long[] menuIds;

    List<Long> permissionIds = new ArrayList<>();
}
