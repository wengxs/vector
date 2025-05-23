package com.vector.wms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vector.wms.pojo.entity.WmsReceiveDetail;
import com.vector.wms.pojo.vo.WmsReceiveVO;

import java.util.List;

public interface WmsReceiveDetailService extends IService<WmsReceiveDetail> {

    List<WmsReceiveVO.Detail> listVOByReceiveId(Long receiveId);
}
