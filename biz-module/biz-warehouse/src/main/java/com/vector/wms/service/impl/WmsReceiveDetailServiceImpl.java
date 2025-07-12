package com.vector.wms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vector.wms.mapper.WmsReceiveDetailMapper;
import com.vector.wms.pojo.entity.WmsReceiveDetail;
import com.vector.wms.pojo.vo.WmsReceiveVO;
import com.vector.wms.service.WmsReceiveDetailService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WmsReceiveDetailServiceImpl extends ServiceImpl<WmsReceiveDetailMapper, WmsReceiveDetail>
        implements WmsReceiveDetailService {

    @Override
    public List<WmsReceiveVO.Detail> listVOByReceiveId(Long receiveId) {
        return baseMapper.selectVOByReceiveId(receiveId);
    }
}
