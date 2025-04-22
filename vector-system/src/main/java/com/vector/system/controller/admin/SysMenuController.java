package com.vector.system.controller.admin;

import com.vector.common.core.result.R;
import com.vector.system.pojo.entity.SysMenu;
import com.vector.system.pojo.form.SysMenuForm;
import com.vector.system.pojo.query.SysMenuQuery;
import com.vector.system.pojo.vo.MenuTree;
import com.vector.system.pojo.vo.SysMenuVO;
import com.vector.system.service.SysMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "系统菜单")
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @Operation(summary = "菜单列表")
    @GetMapping("/list")
    @PreAuthorize("@ss.hasAuthority('sys:menu:query')")
    public R<List<SysMenuVO>> list(SysMenuQuery query) {
        List<SysMenuVO> list = sysMenuService.listTree();
        return R.ok(filterQuery(list, query));
    }

    private List<SysMenuVO> filterQuery(List<SysMenuVO> list, SysMenuQuery query) {
        return list.stream().filter(item -> {
            if (StringUtils.isNotBlank(query.getMenuName()) && item.getMenuName().contains(query.getMenuName())) {
                return true;
            }
            item.setChildren(filterQuery(item.getChildren(), query));
            if (!CollectionUtils.isEmpty(item.getChildren()) || StringUtils.isBlank(query.getMenuName())) {
                return true;
            } else {
                return item.getMenuName().contains(query.getMenuName());
            }
        }).toList();
    }

    @Operation(summary = "获取路由菜单")
    @GetMapping("/router")
    public R<List<MenuTree>> router() {
        return R.ok(sysMenuService.routerTree());
    }

    @Operation(summary = "获取菜单")
    @GetMapping("/{id}")
    @PreAuthorize("@ss.hasAuthority('sys:menu:query')")
    public R<SysMenu> get(@Parameter(description = "菜单ID") @PathVariable Long id) {
        return R.ok(sysMenuService.getById(id));
    }

    @Operation(summary = "新增菜单")
    @PostMapping
    @PreAuthorize("@ss.hasAuthority('sys:menu:add')")
    public R<?> add(@RequestBody SysMenuForm menuForm) {
        sysMenuService.save(menuForm);
        return R.ok();
    }

    @Operation(summary = "修改菜单")
    @PutMapping
    @PreAuthorize("@ss.hasAuthority('sys:menu:edit')")
    public R<?> update(@RequestBody SysMenu sysMenu) {
        sysMenuService.updateById(sysMenu);
        return R.ok();
    }

    @Operation(summary = "删除菜单")
    @DeleteMapping("/{id}")
    @PreAuthorize("@ss.hasAuthority('sys:menu:del')")
    public R<?> delete(@Parameter(description = "菜单ID") @PathVariable Long id) {
        sysMenuService.removeAllById(id, true);
        return R.ok();
    }

    @Operation(summary = "获取菜单树")
    @GetMapping("/tree")
    public R<List<MenuTree>> tree() {
        return R.ok(sysMenuService.menuTree());
    }

}
