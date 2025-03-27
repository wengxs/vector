package com.vector.system.controller.admin;

import com.vector.common.core.constant.SecurityConstant;
import com.vector.common.core.result.R;
import com.vector.common.core.util.BizAssert;
import com.vector.common.security.util.SecurityUtils;
import com.vector.system.pojo.dto.SysCurrentDTO;
import com.vector.system.pojo.entity.SysMenu;
import com.vector.system.pojo.entity.SysRole;
import com.vector.system.pojo.entity.SysUser;
import com.vector.system.pojo.vo.CurrentUserVO;
import com.vector.system.pojo.vo.RouterVO;
import com.vector.system.service.SysMenuService;
import com.vector.system.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sys/current")
@Slf4j
public class SysCurrentController {

//    @Autowired
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysMenuService sysMenuService;

    @GetMapping("/profile")
    public R<CurrentUserVO> userProfile() {
        SysUser sysUser = sysUserService.getById(SecurityUtils.getUserId());
        CurrentUserVO currentUserVO = new CurrentUserVO();
        currentUserVO.setUsername(sysUser.getUsername());
        currentUserVO.setNickname(sysUser.getNickname());
        currentUserVO.setAvatar(sysUser.getAvatar());
        currentUserVO.setMobile(sysUser.getMobile());
        if (SecurityConstant.ID_ADMIN.equals(sysUser.getId())) {
            currentUserVO.setRoles(Collections.singleton(SecurityConstant.ROLE_KEY_ADMIN));
            currentUserVO.setPermissions(Collections.singleton(SecurityConstant.PERMISSION_ADMIN));
        } else {
            List<SysRole> sysRoles = sysUserService.listUserRole(sysUser.getId());
            List<SysMenu> sysMenus = sysMenuService.listByUserId(sysUser.getId());
            currentUserVO.setRoles(sysRoles.stream()
                    .map(SysRole::getRoleKey).collect(Collectors.toSet()));
            currentUserVO.setPermissions(sysMenus.stream()
                    .map(SysMenu::getPermission)
                    .filter(StringUtils::isNotBlank).collect(Collectors.toSet()));
        }
        return R.ok(currentUserVO);
    }

    @PutMapping("/profile")
    public R<?> updateProfile(@RequestBody SysCurrentDTO currentDTO) {
        SysUser sysUser = sysUserService.getById(SecurityUtils.getUserId());
        sysUser.setAvatar(currentDTO.getAvatar());
        sysUser.setMobile(currentDTO.getMobile());
        sysUserService.updateById(sysUser);
        return R.ok();
    }

    @GetMapping("/menu")
    public R<List<RouterVO>> userMenu() {
        List<RouterVO> menus = sysMenuService.getRouters(SecurityUtils.getUserId());
        return R.ok(menus);
    }

    @PutMapping("/password")
    public R<?> updatePassword(@RequestBody SysCurrentDTO currentDTO) {
        Long userId = SecurityUtils.getUserId();
        BizAssert.notNull(userId, "未登录");
        BizAssert.hasText(currentDTO.getOldPassword(), "旧密码不能为空");
        BizAssert.hasText(currentDTO.getNewPassword(), "新密码不能为空");
        SysUser sysUser = sysUserService.getById(userId);
        boolean isMatches = passwordEncoder.matches(currentDTO.getOldPassword(), sysUser.getPassword());
        BizAssert.isTrue(isMatches, "旧密码错误");
        String newPassword = passwordEncoder.encode(currentDTO.getNewPassword());
        sysUserService.updatePassword(sysUser.getUsername(), newPassword);
        return R.ok();
    }

}
