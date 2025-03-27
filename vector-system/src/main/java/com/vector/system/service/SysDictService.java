package com.vector.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vector.system.pojo.entity.SysDict;
import com.vector.system.pojo.vo.SysDictVO;
import com.vector.system.pojo.query.SysDictQuery;

/**
 * 字典 Service
 * @author wengxs
 */
public interface SysDictService extends IService<SysDict> {

    SysDictVO getVOById(Long id);

    IPage<SysDictVO> pageVO(IPage<?> page, SysDictQuery query);
}
