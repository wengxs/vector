package com.vector.scm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vector.scm.pojo.entity.ScmPurchaseDetail;
import com.vector.scm.pojo.vo.ScmPurchaseVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScmPurchaseDetailMapper extends BaseMapper<ScmPurchaseDetail> {

    List<ScmPurchaseVO.Detail> selectVOByPurchaseId(Long purchaseId);
}
