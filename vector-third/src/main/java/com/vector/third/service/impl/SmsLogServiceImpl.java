package com.vector.third.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vector.third.mapper.SmsLogMapper;
import com.vector.third.pojo.entity.SmsLog;
import com.vector.third.service.SmsLogService;
import org.springframework.stereotype.Service;

/**
 * 短信发送记录 ServiceImpl
 * @author wengxs
 * @date 2025/04/02
 */
@Service
public class SmsLogServiceImpl extends ServiceImpl<SmsLogMapper, SmsLog>
        implements SmsLogService {
}
