package com.vector.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vector.system.entity.SysMenu;
import com.vector.system.vo.MenuTree;
import com.vector.system.vo.RouterVo;
import com.vector.system.vo.SysMenuVo;

import java.util.List;

public interface SysMenuService extends IService<SysMenu> {

    List<SysMenu> listByUserId(Long userId);

    List<RouterVo> getRouters(Long userId);

    List<MenuTree> menuTree();

    List<Long> listIdsByRoleId(Long roleId);

    List<SysMenuVo> listTree();

    List<MenuTree> routerTree();
}
