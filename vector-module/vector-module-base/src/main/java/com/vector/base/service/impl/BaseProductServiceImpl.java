package com.vector.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vector.base.entity.BaseProduct;
import com.vector.base.mapper.BaseProductMapper;
import com.vector.base.service.BaseProductService;
import org.springframework.stereotype.Service;

/**
 * @author Zorg
 * @date 2022/3/26
 */
@Service
public class BaseProductServiceImpl extends ServiceImpl<BaseProductMapper, BaseProduct>
        implements BaseProductService {
}
