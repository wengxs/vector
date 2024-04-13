package com.vector.info.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vector.info.entity.InfoProduct;
import com.vector.info.mapper.InfoProductMapper;
import com.vector.info.service.InfoProductService;
import org.springframework.stereotype.Service;

@Service
public class InfoProductServiceImpl extends ServiceImpl<InfoProductMapper, InfoProduct> implements InfoProductService {

}
