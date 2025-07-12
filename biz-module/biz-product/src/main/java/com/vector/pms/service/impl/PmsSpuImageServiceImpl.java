package com.vector.pms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vector.pms.mapper.PmsSpuImageMapper;
import com.vector.pms.pojo.entity.PmsSpuImage;
import com.vector.pms.service.PmsSpuImageService;
import org.springframework.stereotype.Service;

/**
 * 商品图片 ServiceImpl
 * @author wengxs
 * @date 2024/05/05
 */
@Service
public class PmsSpuImageServiceImpl extends ServiceImpl<PmsSpuImageMapper, PmsSpuImage>
        implements PmsSpuImageService {
}
