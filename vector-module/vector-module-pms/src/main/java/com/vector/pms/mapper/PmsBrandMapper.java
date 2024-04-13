package com.vector.pms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vector.common.redis.config.MyBatisRedisCache;
import com.vector.pms.entity.PmsBrand;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Zorg
 * 2020/5/16 14:17
 */
@Mapper
@CacheNamespace(implementation= MyBatisRedisCache.class, eviction=MyBatisRedisCache.class)
public interface PmsBrandMapper extends BaseMapper<PmsBrand> {

}
