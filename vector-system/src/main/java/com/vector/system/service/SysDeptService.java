package com.vector.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vector.system.pojo.entity.SysDept;
import com.vector.system.pojo.query.SysDeptQuery;
import com.vector.system.pojo.vo.SysDeptTree;
import com.vector.system.pojo.vo.SysDeptVO;

import java.util.List;

/**
 * 部门 Service
 * @author wengxs
 */
public interface SysDeptService extends IService<SysDept> {

    SysDeptVO getVOById(Long id);

    IPage<SysDeptVO> pageVO(IPage<?> page, SysDeptQuery query);

    List<SysDeptVO> listTree();

    List<SysDeptTree> deptTree();
}
