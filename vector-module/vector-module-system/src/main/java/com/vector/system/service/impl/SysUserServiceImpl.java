package com.vector.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vector.common.core.util.BizAssert;
import com.vector.system.entity.SysMenu;
import com.vector.system.entity.SysRole;
import com.vector.system.entity.SysUser;
import com.vector.system.entity.SysUserRole;
import com.vector.system.mapper.SysUserMapper;
import com.vector.system.service.SysRoleService;
import com.vector.system.service.SysUserRoleService;
import com.vector.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Zorg
 * 2020/5/16 01:25
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    public void updatePassword(String username, String password) {
        baseMapper.updatePassword(username, password);
    }

    @Override
    public boolean exists(String username) {
        return baseMapper.selectOneByUsername(username) != null;
    }

    @Override
    public List<SysRole> listUserRole(Long userId) {
        return sysRoleService.listByUserId(userId);
    }

    @Override
    @Transactional
    public void saveOrUpdate(SysUser sysUser, List<Long> roleIds) {
        if (sysUser.getId() == null) {
            baseMapper.insert(sysUser);
        } else {
            baseMapper.updateById(sysUser);
            sysUserRoleService.remove(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, sysUser.getId()));
        }
        // 保存角色
        List<SysUserRole> userRoles = roleIds.stream().distinct()
                .map(roleId -> new SysUserRole(sysUser.getId(), roleId))
                .collect(Collectors.toList());
        sysUserRoleService.saveBatch(userRoles);
    }

    @Override
    @Transactional
    public void delete(Long[] ids) {
        for (Long id : ids) {
            BizAssert.isTrue(id != 1L, "不允许操作超级管理员");
            baseMapper.deleteById(id);
            sysUserRoleService.remove(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, id));
        }
    }
}
