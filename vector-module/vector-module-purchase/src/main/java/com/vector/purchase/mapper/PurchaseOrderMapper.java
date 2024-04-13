package com.vector.purchase.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vector.purchase.entity.PurchaseOrder;
import com.vector.purchase.vo.PurchaseOrderVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PurchaseOrderMapper extends BaseMapper<PurchaseOrder> {

    PurchaseOrderVo selectVoById(Long id);

    IPage<PurchaseOrderVo> selectVoPage(@Param("page") IPage<?> page, @Param("q") PurchaseOrderVo query);

}
