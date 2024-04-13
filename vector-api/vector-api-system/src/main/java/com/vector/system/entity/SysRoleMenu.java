package com.vector.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Zorg
 * 2020/6/2 17:44
 */
@Data
@TableName("sys_role_menu")
@NoArgsConstructor
@AllArgsConstructor
public class SysRoleMenu {

    private Long roleId;

    private Long menuId;

}
