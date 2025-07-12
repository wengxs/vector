package com.vector.pms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vector.pms.pojo.dto.PmsSpuDTO;
import com.vector.pms.pojo.entity.PmsSpu;
import com.vector.pms.pojo.query.PmsSpuQuery;
import com.vector.pms.pojo.vo.PmsSpuVO;

/**
 * 商品信息 Service
 * @author wengxs
 * @date 2024/05/05
 */
public interface PmsSpuService extends IService<PmsSpu> {

    PmsSpuVO getVOById(Long id);

    IPage<PmsSpuVO> pageVO(IPage<?> page, PmsSpuQuery query);

    void saveSpu(PmsSpuDTO spuDTO);

    void updateSpu(PmsSpuDTO spuDTO);

    void removeDraftById(Long id);
}
