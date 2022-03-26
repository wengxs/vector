package com.vector.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vector.base.entity.BaseProduct;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Zorg
 * @date 2022/3/26
 */
@Mapper
public interface BaseProductMapper extends BaseMapper<BaseProduct> {
}
