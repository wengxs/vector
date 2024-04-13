package com.vector.sale.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vector.sale.entity.SaleOrder;
import com.vector.sale.vo.SaleOrderVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SaleOrderMapper extends BaseMapper<SaleOrder> {

    SaleOrderVo selectVoById(Long id);

    IPage<SaleOrderVo> selectVoPage(@Param("page") IPage<?> page, @Param("q") SaleOrderVo query);

}
