package com.vector.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vector.system.entity.SysRoleMenu;
import com.vector.system.mapper.SysRoleMenuMapper;
import com.vector.system.service.SysRoleMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wengxs
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {
    @Override
    public List<Long> listAllMenuIds() {
        return baseMapper.selectAllMenuIds();
    }
}
