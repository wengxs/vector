package com.vector.third.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vector.third.mapper.ThirdSmsLogMapper;
import com.vector.third.pojo.entity.ThirdSmsLog;
import com.vector.third.service.ThirdSmsLogService;
import org.springframework.stereotype.Service;

/**
 * 短信发送记录 ServiceImpl
 * @author wengxs
 * @date 2025/04/02
 */
@Service
public class ThirdSmsLogServiceImpl extends ServiceImpl<ThirdSmsLogMapper, ThirdSmsLog>
        implements ThirdSmsLogService {
}
