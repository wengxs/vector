package com.vector.sale.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vector.sale.entity.SaleOrderDetail;
import com.vector.sale.mapper.SaleOrderDetailMapper;
import com.vector.sale.service.SaleOrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class SaleOrderDetailServiceImpl extends ServiceImpl<SaleOrderDetailMapper, SaleOrderDetail>
        implements SaleOrderDetailService {

}
