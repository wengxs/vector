package com.vector.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vector.system.entity.SysDict;
import com.vector.system.mapper.SysDictMapper;
import com.vector.system.query.SysDictQuery;
import com.vector.system.service.SysDictService;
import com.vector.system.vo.SysDictVO;
import org.springframework.stereotype.Service;

/**
 * 字典 ServiceImpl
 * @author wengxs
 */
@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict>
        implements SysDictService {

    @Override
    public SysDictVO getVOById(Long id) {
        return baseMapper.selectVOById(id);
    }

    @Override
    public IPage<SysDictVO> pageVO(IPage<?> page, SysDictQuery query) {
        return baseMapper.selectVOPage(page, query);
    }
}
