package com.vector.app.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vector.app.mapper.AppUserMapper;
import com.vector.app.pojo.entity.AppUser;
import com.vector.app.pojo.query.AppUserQuery;
import com.vector.app.pojo.vo.AppUserVO;
import com.vector.app.service.AppUserService;
import org.springframework.stereotype.Service;

/**
 * 用户信息 ServiceImpl
 * @author wengxs
 * @date 2025/03/31
 */
@Service
public class AppUserServiceImpl extends ServiceImpl<AppUserMapper, AppUser>
        implements AppUserService {

    @Override
    public AppUserVO getVOById(Long id) {
        return baseMapper.selectVOById(id);
    }

    @Override
    public IPage<AppUserVO> pageVO(IPage<?> page, AppUserQuery query) {
        return baseMapper.selectVOPage(page, query);
    }
}
