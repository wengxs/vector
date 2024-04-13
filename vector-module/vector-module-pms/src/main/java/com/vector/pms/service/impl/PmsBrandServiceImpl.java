package com.vector.pms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vector.pms.entity.PmsBrand;
import com.vector.pms.mapper.PmsBrandMapper;
import com.vector.pms.service.PmsBrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Created by Zorg
 * 2020/5/16 01:25
 */
@Service
@Slf4j
public class PmsBrandServiceImpl extends ServiceImpl<PmsBrandMapper, PmsBrand> implements PmsBrandService {

//    @Override
//    @Cacheable(cacheNames = "getBrand", key = "'pms:brand:'+#id")
//    public PmsBrand getById(Serializable id) {
//        log.info("数据库获取");
//        return super.getById(id);
//    }
//
//    @Override
//    @CachePut(cacheNames = "getBrand", key = "'pms:brand:'+#entity.id")
//    public boolean save(PmsBrand entity) {
//        return super.save(entity);
//    }
//
//    @Override
//    @CachePut(cacheNames = "getBrand", key = "'pms:brand:'+#entity.id")
//    public boolean updateById(PmsBrand entity) {
//        return super.updateById(entity);
//    }

}
