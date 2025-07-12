package com.vector.scm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vector.scm.pojo.entity.ScmPurchaseDetail;
import com.vector.scm.pojo.vo.ScmPurchaseVO;

import java.util.List;

public interface ScmPurchaseDetailService extends IService<ScmPurchaseDetail> {

    List<ScmPurchaseVO.Detail> listVOByPurchaseId(Long purchaseId);
}
