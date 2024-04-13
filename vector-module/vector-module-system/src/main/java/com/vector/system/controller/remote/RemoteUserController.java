package com.vector.system.controller.remote;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.vector.common.core.result.R;
import com.vector.system.entity.SysMenu;
import com.vector.system.entity.SysRole;
import com.vector.system.entity.SysUser;
import com.vector.system.service.SysMenuService;
import com.vector.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/remote/user")
public class RemoteUserController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysMenuService sysMenuService;

    @GetMapping("/load/{username}")
    public R<SysUser> loadUserByUsername(@PathVariable String username) {
        SysUser sysUser = sysUserService.getOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username));
        return R.ok(sysUser);
    }

    @GetMapping("/roles/{userId}")
    public R<List<SysRole>> getRolesByUserId(@PathVariable Long userId) {
        List<SysRole> roles = sysUserService.listUserRole(userId);
        return R.ok(roles);
    }

    @GetMapping("/menus/{userId}")
    public R<List<SysMenu>> getMenusByUserId(@PathVariable Long userId) {
        List<SysMenu> menus = sysMenuService.listByUserId(userId);
        return R.ok(menus);
    }
}
