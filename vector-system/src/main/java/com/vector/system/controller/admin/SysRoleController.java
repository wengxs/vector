package com.vector.system.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vector.common.core.pagination.Pageable;
import com.vector.common.core.result.PageResult;
import com.vector.common.core.result.R;
import com.vector.common.core.util.BizAssert;
import com.vector.system.pojo.entity.SysRole;
import com.vector.system.pojo.form.SysRoleForm;
import com.vector.system.pojo.query.SysRoleQuery;
import com.vector.system.pojo.vo.SysRoleVO;
import com.vector.system.service.SysMenuService;
import com.vector.system.service.SysRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Tag(name = "系统角色")
@RestController
@RequestMapping("/sys/role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysMenuService sysMenuService;

    @Operation(summary = "角色分页列表")
    @GetMapping("/list")
    @PreAuthorize("@ss.hasAuthority('sys:role:query')")
    public R<PageResult> list(SysRoleQuery query) {
        IPage<SysRoleVO> page = sysRoleService.pageVO(Pageable.getPage(query), query);
        return R.page(page.getRecords(), page.getTotal());
    }

    @Operation(summary = "获取角色")
    @GetMapping("/{id}")
    @PreAuthorize("@ss.hasAuthority('sys:role:query')")
    public R<SysRoleVO> get(@Parameter(description = "角色ID") @PathVariable Long id) {
        SysRoleVO roleVO = sysRoleService.getVOById(id);
        roleVO.setMenuIds(sysMenuService.listIdsByRoleId(id));
        return R.ok(roleVO);
    }

    @Operation(summary = "新增角色")
    @PostMapping
    @PreAuthorize("@ss.hasAuthority('sys:role:add')")
    public R<?> add(@RequestBody SysRoleForm roleForm) {
        BizAssert.notEmpty(roleForm.getMenuIds(), "必须选择一个菜单");
        BizAssert.isTrue(!sysRoleService.exists(roleForm.getRoleName()), roleForm.getRoleName() + "已存在");
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(roleForm, sysRole);
        sysRole.setId(null);
        sysRoleService.saveOrUpdate(sysRole, Arrays.asList(roleForm.getMenuIds()));
        return R.ok();
    }

    @Operation(summary = "修改角色")
    @PutMapping
    @PreAuthorize("@ss.hasAuthority('sys:role:edit')")
    public R<?> update(@RequestBody SysRoleForm roleForm) {
        BizAssert.isTrue(roleForm.getId() != 1L, "不允许操作超级管理员");
        BizAssert.notEmpty(roleForm.getMenuIds(), "必须选择一个菜单");
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(roleForm, sysRole);
        sysRoleService.saveOrUpdate(sysRole, Arrays.asList(roleForm.getMenuIds()));
        return R.ok();
    }

    @Operation(summary = "删除角色")
    @DeleteMapping("/{ids}")
    @PreAuthorize("@ss.hasAuthority('sys:role:del')")
    public R<?> delete(@Parameter(description = "角色ID数组") @PathVariable Long[] ids) {
        sysRoleService.delete(ids);
        return R.ok();
    }

    @Operation(summary = "获取所有角色")
    @GetMapping("/all")
    public R<List<SysRole>> roles() {
        return R.ok(sysRoleService.list());
    }

}
