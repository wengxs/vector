package com.vector.third.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vector.third.pojo.entity.ThirdSmsLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * 短信发送记录 Mapper
 * @author wengxs
 * @date 2025/04/02
 */
@Mapper
public interface ThirdSmsLogMapper extends BaseMapper<ThirdSmsLog> {
}
