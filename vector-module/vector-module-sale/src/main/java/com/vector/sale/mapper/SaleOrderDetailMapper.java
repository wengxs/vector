package com.vector.sale.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vector.sale.entity.SaleOrderDetail;
import com.vector.sale.vo.SaleOrderVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SaleOrderDetailMapper extends BaseMapper<SaleOrderDetail> {

    List<SaleOrderVo.Detail> selectVoByOrderId(Long orderId);
}
