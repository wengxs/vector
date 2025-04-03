package com.vector.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vector.app.pojo.entity.AppUser;
import com.vector.app.pojo.query.AppUserQuery;
import com.vector.app.pojo.vo.AppUserVO;

/**
 * 用户信息 Service
 * @author wengxs
 * @date 2025/03/31
 */
public interface AppUserService extends IService<AppUser> {

    AppUserVO getVOById(Long id);

    IPage<AppUserVO> pageVO(IPage<?> page, AppUserQuery query);
}
