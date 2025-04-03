package com.vector.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vector.app.pojo.entity.AppUser;
import com.vector.app.pojo.query.AppUserQuery;
import com.vector.app.pojo.vo.AppUserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户信息 Mapper
 * @author wengxs
 * @date 2025/03/31
 */
@Mapper
public interface AppUserMapper extends BaseMapper<AppUser> {

    AppUserVO selectVOById(Long id);

    IPage<AppUserVO> selectVOPage(@Param("page") IPage<?> page, @Param("q") AppUserQuery query);
}
