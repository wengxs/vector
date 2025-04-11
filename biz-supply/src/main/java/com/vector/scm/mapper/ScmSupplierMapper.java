package com.vector.scm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vector.scm.pojo.entity.ScmSupplier;
import com.vector.scm.pojo.query.ScmSupplierQuery;
import com.vector.scm.pojo.vo.ScmSupplierVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 供应商信息 Mapper
 * @author wengxs
 * @date 2024/05/12
 */
@Mapper
public interface ScmSupplierMapper extends BaseMapper<ScmSupplier> {

    ScmSupplierVO selectVOById(Long id);

    IPage<ScmSupplierVO> selectVOPage(@Param("page") IPage<?> page, @Param("q") ScmSupplierQuery query);
}
