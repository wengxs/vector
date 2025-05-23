package com.vector.wms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vector.wms.mapper.WmsProductBatchMapper;
import com.vector.wms.pojo.entity.WmsProductBatch;
import com.vector.wms.service.WmsProductBatchService;
import org.springframework.stereotype.Service;

@Service
public class WmsProductBatchServiceImpl extends ServiceImpl<WmsProductBatchMapper, WmsProductBatch>
        implements WmsProductBatchService {

}
