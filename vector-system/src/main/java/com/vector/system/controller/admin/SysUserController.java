package com.vector.system.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vector.common.core.constant.SecurityConstant;
import com.vector.common.core.pagination.Pageable;
import com.vector.common.core.result.PageResult;
import com.vector.common.core.result.R;
import com.vector.common.core.util.BizAssert;
import com.vector.system.enums.SysUserStatus;
import com.vector.system.pojo.entity.SysUser;
import com.vector.system.pojo.form.SysUserForm;
import com.vector.system.pojo.query.SysUserQuery;
import com.vector.system.pojo.vo.SysUserVO;
import com.vector.system.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Tag(name = "系统用户")
@RestController
@RequestMapping("/sys/user")
@Slf4j
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserService sysRoleService;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Operation(summary = "用户分页列表")
    @GetMapping("/list")
    @PreAuthorize("@ss.hasAuthority('sys:user:query')")
    public R<PageResult> list(SysUserQuery query) {
        IPage<SysUserVO> page = sysUserService.pageVO(Pageable.getPage(query), query);
        return R.page(page.getRecords(), page.getTotal());
    }

    @Operation(summary = "获取用户")
    @GetMapping("/{id}")
    @PreAuthorize("@ss.hasAuthority('sys:user:query')")
    public R<SysUserVO> get(@Parameter(description = "用户ID") @PathVariable Long id) {
        SysUserVO userVO = sysUserService.getVOById(id);
        userVO.setRoleIds(sysRoleService.listIdsByUserId(id));
        return R.ok(userVO);
    }

    @Operation(summary = "新增用户")
    @PostMapping
    @PreAuthorize("@ss.hasAuthority('sys:user:add')")
    public R<?> add(@RequestBody SysUserForm userForm) {
        BizAssert.notEmpty(userForm.getRoleIds(), "必须选择一个角色");
        BizAssert.isTrue(!sysUserService.exists(userForm.getUsername()), userForm.getUsername() + "已存在");
        String password = userForm.getPassword();
        password = passwordEncoder.encode(
                StringUtils.isNotBlank(password) ? password : SecurityConstant.DEFAULT_PASSWORD);
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userForm, sysUser);
        sysUser.setId(null);
        sysUser.setPassword(password);
        sysUserService.saveOrUpdate(sysUser, Arrays.asList(userForm.getRoleIds()));
        return R.ok();
    }

    @Operation(summary = "修改用户")
    @PutMapping
    @PreAuthorize("@ss.hasAuthority('sys:user:edit')")
    public R<?> update(@RequestBody SysUserForm userForm) {
        BizAssert.isTrue(userForm.getId() != 1L, "不允许操作超级管理员");
        BizAssert.notEmpty(userForm.getRoleIds(), "必须选择一个角色");
        if (StringUtils.isNotBlank(userForm.getPassword())) {
            userForm.setPassword(passwordEncoder.encode(userForm.getPassword()));
        } else {
            userForm.setPassword(null);
        }
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userForm, sysUser);
        sysUserService.saveOrUpdate(sysUser, Arrays.asList(userForm.getRoleIds()));
        return R.ok();
    }

    @Operation(summary = "修改用户状态")
    @PutMapping("/{id}/{userStatus}")
    @PreAuthorize("@ss.hasAuthority('sys:user:edit')")
    public R<?> updateUserStatus(@Parameter(description = "用户ID") @PathVariable Long id,
                                 @Parameter(description = "用户状态") @PathVariable SysUserStatus userStatus) {
        BizAssert.isTrue(id != 1L, "不允许操作超级管理员");
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        sysUser.setUserStatus(userStatus);
        sysUserService.updateById(sysUser);
        return R.ok();
    }
}
