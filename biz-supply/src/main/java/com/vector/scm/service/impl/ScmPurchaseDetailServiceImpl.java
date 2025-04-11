package com.vector.scm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vector.scm.mapper.ScmPurchaseDetailMapper;
import com.vector.scm.pojo.entity.ScmPurchaseDetail;
import com.vector.scm.pojo.vo.ScmPurchaseVO;
import com.vector.scm.service.ScmPurchaseDetailService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScmPurchaseDetailServiceImpl extends ServiceImpl<ScmPurchaseDetailMapper, ScmPurchaseDetail> implements ScmPurchaseDetailService {

    @Override
    public List<ScmPurchaseVO.Detail> listVOByPurchaseId(Long purchaseId) {
        return baseMapper.selectVOByPurchaseId(purchaseId);
    }
}
