package com.vector.system.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vector.common.core.constant.SecurityConstant;
import com.vector.common.core.util.BizAssert;
import com.vector.system.enums.SysMenuPermission;
import com.vector.system.enums.SysMenuType;
import com.vector.system.mapper.SysMenuMapper;
import com.vector.system.pojo.entity.SysMenu;
import com.vector.system.pojo.entity.SysRoleMenu;
import com.vector.system.pojo.form.SysMenuForm;
import com.vector.system.pojo.vo.MenuTree;
import com.vector.system.pojo.vo.RouterVO;
import com.vector.system.pojo.vo.SysMenuVO;
import com.vector.system.service.SysMenuService;
import com.vector.system.service.SysRoleMenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wengxs
 */
@Service
@Slf4j
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Override
    @Transactional
    public void save(SysMenuForm menuForm) {
        SysMenu sysMenu = new SysMenu();
        BeanUtils.copyProperties(menuForm, sysMenu);
        baseMapper.insert(sysMenu);
        if (sysMenu.getParentId() == 0L || SysMenuType.BUTTON.equals(sysMenu.getType())) {
            return;
        }
        if (!CollectionUtils.isEmpty(menuForm.getSubPermissions())) {
            String permissionPrefix;
            if (StringUtils.isNotBlank(menuForm.getPermission())) {
                permissionPrefix = menuForm.getPermission().substring(0, menuForm.getPermission().lastIndexOf(":") + 1);
            } else {
                permissionPrefix = menuForm.getComponent().replace("/", ":");
                permissionPrefix = permissionPrefix.substring(0, permissionPrefix.lastIndexOf(":") + 1);
            }
            for (int i = 0; i < menuForm.getSubPermissions().size(); i++) {
                SysMenuPermission subPermission = menuForm.getSubPermissions().get(i);
                SysMenu subMenu = new SysMenu();
                subMenu.setParentId(sysMenu.getId());
                subMenu.setType(SysMenuType.BUTTON);
                subMenu.setMenuName(sysMenu.getMenuName() + "-" + subPermission.getDesc());
                subMenu.setSort(i+1);
                subMenu.setPermission(permissionPrefix + subPermission.name().toLowerCase());
                baseMapper.insert(subMenu);
            }
        }
    }

    @Override
    @Transactional
    public void removeAllById(Long id, boolean assignCheck) {
        List<Long> assignedMenuIds = sysRoleMenuService.listAllMenuIds();
        removeChildren(id, assignCheck, assignedMenuIds);
    }

    private void removeChildren(Long id, boolean assignCheck, List<Long> assignedMenuIds) {
        if (assignedMenuIds.contains(id)) {
            BizAssert.isTrue(!assignCheck, "菜单已分配，请先删除角色菜单");
            sysRoleMenuService.remove(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getMenuId, id));
        }
        List<SysMenu> subMenus = baseMapper.selectList(
                new LambdaQueryWrapper<SysMenu>().eq(SysMenu::getParentId, id));
        for (SysMenu subMenu : subMenus) {
            removeChildren(subMenu.getId(), assignCheck, assignedMenuIds);
        }
        baseMapper.deleteById(id);
    }

    @Override
    public List<SysMenu> listByUserId(Long userId) {
        return baseMapper.selectAllByUserId(userId);
    }

    @Override
    public List<RouterVO> getRouters(Long userId) {
        List<SysMenu> menus;
        if (SecurityConstant.ID_ADMIN.equals(userId)) {
            menus = baseMapper.selectList(new LambdaQueryWrapper<SysMenu>()
                    .eq(SysMenu::getType, SysMenuType.MENU)
                    .orderByAsc(SysMenu::getSort)
            );
        } else {
            menus = baseMapper.selectAllByUserId(userId);
        }
        log.info("menus={}", JSON.toJSONString(menus));
        return genRouters(menus, 0L);
    }

    private List<RouterVO> genRouters(List<SysMenu> menus, Long parentId) {
        List<RouterVO> routers = new ArrayList<>();
        menus.stream()
                .filter(menu -> SysMenuType.MENU.equals(menu.getType()))
                .filter(menu -> parentId.equals(menu.getParentId()))
                .forEach(menu -> {
                    RouterVO router = new RouterVO();
                    router.setName(convertToName(menu.getComponent()));
                    router.setPath(menu.getPath());
                    router.setComponent(StringUtils.isEmpty(menu.getComponent()) ? "Layout" : menu.getComponent());
                    router.setMeta(new RouterVO.Meta(menu.getMenuName(), menu.getIcon(), menu.getHidden(), true));
                    router.setChildren(genRouters(menus, menu.getId()));
                    routers.add(router);
                });
        return routers;
    }

    private String convertToName(String component) {
        if (StringUtils.isEmpty(component)) return "";
        String[] paths = component.split("/");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < paths.length - 1; i++) {
            sb.append(StringUtils.capitalize(paths[i]));
        }
        if (!"index".equals(paths[paths.length - 1])) {
            sb.append(StringUtils.capitalize(paths[paths.length - 1]));
        }
        return sb.toString();
    }

    @Override
    public List<MenuTree> menuTree() {
        List<SysMenu> menus = baseMapper.selectList(new LambdaQueryWrapper<SysMenu>().orderByAsc(SysMenu::getSort));
        return genMenuTree(menus, 0L);
    }

    private List<MenuTree> genMenuTree(List<SysMenu> menus, Long parentId) {
        List<MenuTree> menuTrees = new ArrayList<>();
        menus.stream()
                .filter(menu -> parentId.equals(menu.getParentId()))
                .forEach(menu -> {
                    MenuTree menuTree = new MenuTree();
                    menuTree.setId(menu.getId());
                    menuTree.setMenuName(menu.getMenuName());
                    menuTree.setChildren(genMenuTree(menus, menu.getId()));
                    menuTrees.add(menuTree);
                });
        return menuTrees;
    }

    @Override
    public List<Long> listIdsByRoleId(Long roleId) {
        return baseMapper.selectIdsByRoleId(roleId);
    }

    @Override
    public List<SysMenuVO> listTree() {
        List<SysMenu> menus = baseMapper.selectList(new LambdaQueryWrapper<SysMenu>().orderByAsc(SysMenu::getSort));
        return genTree(menus, 0L);
    }

    private List<SysMenuVO> genTree(List<SysMenu> menus, Long parentId) {
        List<SysMenuVO> sysMenus = new ArrayList<>();
        menus.stream()
                .filter(menu -> parentId.equals(menu.getParentId()))
                .forEach(menu -> {
                    SysMenuVO menuVO = new SysMenuVO();
                    BeanUtils.copyProperties(menu, menuVO);
                    menuVO.setId(menu.getId());
                    menuVO.setChildren(genTree(menus, menu.getId()));
                    sysMenus.add(menuVO);
                });
        return sysMenus;
    }

    @Override
    public List<MenuTree> routerTree() {
        List<SysMenu> menus = baseMapper.selectList(new LambdaQueryWrapper<SysMenu>()
                .eq(SysMenu::getType, SysMenuType.MENU)
                .orderByAsc(SysMenu::getSort));
        return genMenuTree(menus, 0L);
    }
}
