package com.vector.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vector.system.pojo.entity.SysMenu;
import com.vector.system.pojo.form.SysMenuForm;
import com.vector.system.pojo.vo.MenuTree;
import com.vector.system.pojo.vo.RouterVO;
import com.vector.system.pojo.vo.SysMenuVO;

import java.util.List;

/**
 * @author wengxs
 */
public interface SysMenuService extends IService<SysMenu> {

    List<SysMenu> listByUserId(Long userId);

    List<RouterVO> getRouters(Long userId);

    List<MenuTree> menuTree();

    List<Long> listIdsByRoleId(Long roleId);

    List<SysMenuVO> listTree();

    List<MenuTree> routerTree();

    void save(SysMenuForm menuForm);

    /**
     * 删除菜单及子菜单
     * @param id 菜单ID
     * @param assignCheck 是否角色分配校验
     */
    void removeAllById(Long id, boolean assignCheck);
}
