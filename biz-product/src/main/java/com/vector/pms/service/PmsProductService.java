package com.vector.pms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vector.pms.pojo.entity.PmsProduct;
import com.vector.pms.pojo.query.PmsProductQuery;
import com.vector.pms.pojo.vo.PmsProductVO;

/**
 * 产品信息 Service
 * @author wengxs
 * @date 2024/05/04
 */
public interface PmsProductService extends IService<PmsProduct> {

    PmsProductVO getVOById(Long id);

    IPage<PmsProductVO> pageVO(IPage<?> page, PmsProductQuery query);
}
