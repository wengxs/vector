package com.vector.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vector.system.entity.SysDict;
import com.vector.system.query.SysDictQuery;
import com.vector.system.vo.SysDictVO;

/**
 * 字典 Service
 * @author wengxs
 */
public interface SysDictService extends IService<SysDict> {

    SysDictVO getVOById(Long id);

    IPage<SysDictVO> pageVO(IPage<?> page, SysDictQuery query);
}
