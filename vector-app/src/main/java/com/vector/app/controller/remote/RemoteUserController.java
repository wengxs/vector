package com.vector.app.controller.remote;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.vector.app.dto.UserAuthInfo;
import com.vector.app.enums.AppUserStatus;
import com.vector.app.pojo.entity.AppUser;
import com.vector.app.service.AppUserService;
import com.vector.common.core.result.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/remote/user")
public class RemoteUserController {

    @Autowired
    private AppUserService appUserService;
    @GetMapping("/load/{mobileOrOpenid}")
    public R<UserAuthInfo> loadUserByMobile(@PathVariable String mobileOrOpenid) {
        AppUser appUser = appUserService.getOne(new LambdaQueryWrapper<AppUser>()
                .eq(AppUser::getMobile, mobileOrOpenid)
                .or()
                .eq(AppUser::getOpenid, mobileOrOpenid)
        );
        if (appUser == null) {
            return R.ok();
        }
        UserAuthInfo userAuthInfo = new UserAuthInfo();
        userAuthInfo.setUserId(appUser.getId());
        userAuthInfo.setMobile(appUser.getMobile());
        userAuthInfo.setOpenid(appUser.getOpenid());
        userAuthInfo.setUsername(appUser.getUsername());
        userAuthInfo.setPassword(appUser.getPassword());
        userAuthInfo.setAvatar(appUser.getAvatar());
        userAuthInfo.setEnabled(AppUserStatus.ENABLED.equals(appUser.getUserStatus()));
        return R.ok(userAuthInfo);
    }
}
