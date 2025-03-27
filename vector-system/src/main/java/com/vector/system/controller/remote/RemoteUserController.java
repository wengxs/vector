package com.vector.system.controller.remote;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.vector.common.core.constant.SecurityConstant;
import com.vector.common.core.enums.DataScope;
import com.vector.common.core.result.R;
import com.vector.system.dto.UserAuthInfo;
import com.vector.system.enums.SysUserStatus;
import com.vector.system.pojo.entity.SysMenu;
import com.vector.system.pojo.entity.SysRole;
import com.vector.system.pojo.entity.SysUser;
import com.vector.system.service.SysMenuService;
import com.vector.system.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/remote/user")
public class RemoteUserController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysMenuService sysMenuService;

    @GetMapping("/load/{username}")
    public R<UserAuthInfo> loadUserByUsername(@PathVariable String username) {
        SysUser sysUser = sysUserService.getOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username));
        if (sysUser == null) {
            return R.ok();
        }
        List<SysRole> sysRoles = sysUserService.listUserRole(sysUser.getId());
        List<SysMenu> sysMenus = sysMenuService.listByUserId(sysUser.getId());
        UserAuthInfo userAuthInfo = new UserAuthInfo();
        userAuthInfo.setUserId(sysUser.getId());
        userAuthInfo.setUsername(sysUser.getUsername());
        userAuthInfo.setNickname(sysUser.getNickname());
        userAuthInfo.setPassword(sysUser.getPassword());
        userAuthInfo.setMobile(sysUser.getMobile());
        userAuthInfo.setEmail(sysUser.getEmail());
        userAuthInfo.setGender(sysUser.getGender());
        userAuthInfo.setAvatar(sysUser.getAvatar());
        userAuthInfo.setDeptId(sysUser.getDeptId());
        userAuthInfo.setDataScope(getDataScope(sysRoles));
        userAuthInfo.setEnabled(SysUserStatus.ENABLED.equals(sysUser.getUserStatus()));
        userAuthInfo.setRoles(sysRoles.stream()
                .map(sysRole -> SecurityConstant.ROLE_PREFIX + sysRole.getRoleKey())
                .collect(Collectors.toSet()));
        userAuthInfo.setPermissions(sysMenus.stream()
                .map(SysMenu::getPermission)
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.toSet()));
        return R.ok(userAuthInfo);
    }

    private String getDataScope(List<SysRole> sysRoles) {
        Set<DataScope> dataScopes = sysRoles.stream().map(SysRole::getDataScope).collect(Collectors.toSet());
        if (dataScopes.contains(DataScope.ALL)) {
            return DataScope.ALL.name();
        } else if (dataScopes.contains(DataScope.DEPT_AND_CHILD)) {
            return DataScope.DEPT_AND_CHILD.name();
        } else if (dataScopes.contains(DataScope.DEPT)) {
            return DataScope.DEPT.name();
        } else {
            return DataScope.SELF.name();
        }
    }
}
