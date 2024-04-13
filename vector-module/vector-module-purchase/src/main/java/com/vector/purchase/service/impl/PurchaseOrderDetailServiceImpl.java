package com.vector.purchase.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vector.purchase.entity.PurchaseOrderDetail;
import com.vector.purchase.mapper.PurchaseOrderDetailMapper;
import com.vector.purchase.service.PurchaseOrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class PurchaseOrderDetailServiceImpl extends ServiceImpl<PurchaseOrderDetailMapper, PurchaseOrderDetail> implements PurchaseOrderDetailService {

}
