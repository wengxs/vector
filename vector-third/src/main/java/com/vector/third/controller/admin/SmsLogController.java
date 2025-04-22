package com.vector.third.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vector.common.core.pagination.Pageable;
import com.vector.common.core.result.PageResult;
import com.vector.common.core.result.R;
import com.vector.third.pojo.entity.SmsLog;
import com.vector.third.pojo.query.SmsLogQuery;
import com.vector.third.service.SmsLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "短信发送记录")
@RestController
@RequestMapping("/sms/log")
public class SmsLogController {

    @Autowired
    private SmsLogService smsLogService;

    @Operation(summary = "短信发送记录分页列表")
    @GetMapping("/list")
    @PreAuthorize("@ss.hasAuthority('sms:log:query')")
    public R<PageResult> list(SmsLogQuery query) {
        IPage<SmsLog> page = smsLogService.page(Pageable.getPage(query),
                new LambdaQueryWrapper<>(SmsLog.class)
                        .eq(StringUtils.isNotBlank(query.getPlatform()),
                                SmsLog::getPlatform, query.getPlatform())
                        .eq(StringUtils.isNotBlank(query.getMobile()),
                                SmsLog::getMobile, query.getMobile())
                        .eq(StringUtils.isNotBlank(query.getTemplateCode()),
                                SmsLog::getTemplateCode, query.getTemplateCode())
                        .eq(StringUtils.isNotBlank(query.getResult()),
                                SmsLog::getResult, query.getResult())
                        .between(StringUtils.isNotBlank(query.getCreateBegin())
                                        && StringUtils.isNotBlank(query.getCreateEnd()),
                                SmsLog::getCreateTime, query.getCreateBegin(), query.getCreateEnd() + " 23:59:59")
        );
        return R.page(page.getRecords(), page.getTotal());
    }

    @Operation(summary = "获取短信发送记录详情")
    @GetMapping("/{id}")
    @PreAuthorize("@ss.hasAuthority('sms:log:query')")
    public R<SmsLog> get(@Parameter(description = "记录ID") @PathVariable Long id) {
        return R.ok(smsLogService.getById(id));
    }

    @Operation(summary = "批量删除短信发送记录")
    @DeleteMapping("/{ids}")
    @PreAuthorize("@ss.hasAuthority('sms:log:del')")
    public R<?> delete(@Parameter(description = "记录ID数组") @PathVariable List<Long> ids) {
        smsLogService.removeByIds(ids);
        return R.ok();
    }
}
