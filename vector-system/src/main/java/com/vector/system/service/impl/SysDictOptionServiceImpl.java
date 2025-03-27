package com.vector.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vector.system.mapper.SysDictOptionMapper;
import com.vector.system.pojo.entity.SysDictOption;
import com.vector.system.service.SysDictOptionService;
import org.springframework.stereotype.Service;

/**
 * 字典选项 ServiceImpl
 * @author wengxs
 */
@Service
public class SysDictOptionServiceImpl extends ServiceImpl<SysDictOptionMapper, SysDictOption>
        implements SysDictOptionService {
}
