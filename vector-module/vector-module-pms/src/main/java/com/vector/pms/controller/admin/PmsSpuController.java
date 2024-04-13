package com.vector.pms.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vector.common.core.pagination.Pageable;
import com.vector.common.core.result.PageResult;
import com.vector.common.core.result.R;
import com.vector.pms.entity.PmsSpu;
import com.vector.pms.service.PmsSpuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pms/spu")
public class PmsSpuController {

    @Autowired
    private PmsSpuService pmsSpuService;

    @GetMapping("/list")
    public R<PageResult> list(@RequestParam Map<String, Object> params) {
        PmsSpu query = Pageable.getQuery(params, PmsSpu.class);
        IPage<PmsSpu> page = pmsSpuService.page(Pageable.getPage(params), new LambdaQueryWrapper<>(PmsSpu.class)
                .like(StringUtils.isNotBlank(query.getSpuName()), PmsSpu::getSpuName, query.getSpuName())
        );
        return R.page(page.getRecords(), page.getTotal());
    }

    @GetMapping("/{id}")
    public R<PmsSpu> get(@PathVariable Long id) {
        return R.ok(pmsSpuService.getById(id));
    }

    @PostMapping
    public R<?> add(@RequestBody PmsSpu pmsSpu) {
        pmsSpuService.save(pmsSpu);
        return R.ok();
    }

    @PutMapping
    public R<?> update(@RequestBody PmsSpu pmsSpu) {
        pmsSpuService.updateById(pmsSpu);
        return R.ok();
    }

    @DeleteMapping("/{ids}")
    public R<?> delete(@PathVariable List<Long> ids) {
        pmsSpuService.removeByIds(ids);
        return R.ok();
    }
}
