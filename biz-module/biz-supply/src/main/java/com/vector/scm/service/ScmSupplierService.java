package com.vector.scm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vector.scm.pojo.entity.ScmSupplier;
import com.vector.scm.pojo.query.ScmSupplierQuery;
import com.vector.scm.pojo.vo.ScmSupplierVO;

/**
 * 供应商信息 Service
 * @author wengxs
 * @date 2024/05/12
 */
public interface ScmSupplierService extends IService<ScmSupplier> {

    ScmSupplierVO getVOById(Long id);

    IPage<ScmSupplierVO> pageVO(IPage<?> page, ScmSupplierQuery query);
}
