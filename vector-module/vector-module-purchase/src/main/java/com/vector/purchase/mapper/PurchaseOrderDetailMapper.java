package com.vector.purchase.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vector.purchase.entity.PurchaseOrderDetail;
import com.vector.purchase.vo.PurchaseOrderVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PurchaseOrderDetailMapper extends BaseMapper<PurchaseOrderDetail> {

    List<PurchaseOrderVo.Detail> selectVoByOrderId(Long orderId);
}
