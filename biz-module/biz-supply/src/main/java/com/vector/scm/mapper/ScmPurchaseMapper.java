package com.vector.scm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vector.scm.pojo.entity.ScmPurchase;
import com.vector.scm.pojo.query.ScmPurchaseQuery;
import com.vector.scm.pojo.vo.ScmPurchaseVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 采购单 Mapper
 * @author wengxs
 * @date 2024/05/12
 */
@Mapper
public interface ScmPurchaseMapper extends BaseMapper<ScmPurchase> {

    ScmPurchaseVO selectVOById(Long id);

    IPage<ScmPurchaseVO> selectVOPage(@Param("page") IPage<?> page, @Param("q") ScmPurchaseQuery query);
}
