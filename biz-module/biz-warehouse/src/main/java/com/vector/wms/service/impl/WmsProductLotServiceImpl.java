package com.vector.wms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vector.wms.mapper.WmsProductLotMapper;
import com.vector.wms.pojo.entity.WmsProductLot;
import com.vector.wms.service.WmsProductLotService;
import org.springframework.stereotype.Service;

@Service
public class WmsProductLotServiceImpl extends ServiceImpl<WmsProductLotMapper, WmsProductLot>
        implements WmsProductLotService {

}
