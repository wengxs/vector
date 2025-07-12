package com.vector.pms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vector.pms.mapper.PmsAttrMapper;
import com.vector.pms.pojo.entity.PmsAttr;
import com.vector.pms.service.PmsAttrService;
import org.springframework.stereotype.Service;

/**
 * 商品属性 ServiceImpl
 * @author wengxs
 * @date 2024/06/02
 */
@Service
public class PmsAttrServiceImpl extends ServiceImpl<PmsAttrMapper, PmsAttr>
        implements PmsAttrService {
}
