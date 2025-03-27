package com.vector.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vector.system.pojo.entity.SysRoleMenu;

import java.util.List;

/**
 * @author wengxs
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {
    List<Long> listAllMenuIds();
}
