package com.vector.third.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vector.common.core.pagination.Pageable;
import com.vector.common.core.result.PageResult;
import com.vector.common.core.result.R;
import com.vector.third.pojo.entity.ThirdSmsLog;
import com.vector.third.pojo.query.ThirdSmsLogQuery;
import com.vector.third.service.ThirdSmsLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 短信发送记录 Controller
 * @author wengxs
 * @date 2025/04/02
 */
@RestController
@RequestMapping("/third/smsLog")
public class ThirdSmsLogController {

    @Autowired
    private ThirdSmsLogService thirdSmsLogService;

    @GetMapping("/list")
    @PreAuthorize("@ss.hasAuthority('third:smsLog:query')")
    public R<PageResult> list(ThirdSmsLogQuery query) {
        IPage<ThirdSmsLog> page = thirdSmsLogService.page(Pageable.getPage(query),
                new LambdaQueryWrapper<>(ThirdSmsLog.class)
                        .eq(StringUtils.isNotBlank(query.getPlatform()),
                                ThirdSmsLog::getPlatform, query.getPlatform())
                        .eq(StringUtils.isNotBlank(query.getMobile()),
                                ThirdSmsLog::getMobile, query.getMobile())
                        .eq(StringUtils.isNotBlank(query.getTemplateCode()),
                                ThirdSmsLog::getTemplateCode, query.getTemplateCode())
                        .eq(StringUtils.isNotBlank(query.getResult()),
                                ThirdSmsLog::getResult, query.getResult())
                        .between(StringUtils.isNotBlank(query.getCreateBegin())
                                        && StringUtils.isNotBlank(query.getCreateEnd()),
                                ThirdSmsLog::getCreateTime, query.getCreateBegin(), query.getCreateEnd() + " 23:59:59")
        );
        return R.page(page.getRecords(), page.getTotal());
    }

    @GetMapping("/{id}")
    @PreAuthorize("@ss.hasAuthority('third:smsLog:query')")
    public R<ThirdSmsLog> get(@PathVariable Long id) {
        return R.ok(thirdSmsLogService.getById(id));
    }

    @DeleteMapping("/{ids}")
    @PreAuthorize("@ss.hasAuthority('third:smsLog:del')")
    public R<?> delete(@PathVariable List<Long> ids) {
        thirdSmsLogService.removeByIds(ids);
        return R.ok();
    }
}
