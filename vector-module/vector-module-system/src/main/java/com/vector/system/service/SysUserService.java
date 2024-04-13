package com.vector.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vector.system.entity.SysRole;
import com.vector.system.entity.SysUser;

import java.util.List;

/**
 * Created by Zorg
 * 2020/5/16 01:24
 */
public interface SysUserService extends IService<SysUser> {

    void updatePassword(String username, String newPassword);

    boolean exists(String username);

    List<SysRole> listUserRole(Long userId);

    void saveOrUpdate(SysUser sysUser, List<Long> roleIds);

    void delete(Long[] ids);
}
