package com.vector.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vector.system.pojo.entity.SysRole;
import com.vector.system.pojo.query.SysRoleQuery;
import com.vector.system.pojo.vo.SysRoleVO;

import java.util.List;

/**
 * @author wengxs
 */
public interface SysRoleService extends IService<SysRole> {

    SysRoleVO getVOById(Long id);

    IPage<SysRoleVO> pageVO(IPage<?> page, SysRoleQuery query);

    boolean exists(String roleName);

    void saveOrUpdate(SysRole sysRole, List<Long> menuIds);

    void delete(Long[] ids);

    List<SysRole> listByUserId(Long userId);
}
