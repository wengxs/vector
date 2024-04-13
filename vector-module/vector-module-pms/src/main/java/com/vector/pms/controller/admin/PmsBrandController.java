package com.vector.pms.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vector.common.core.pagination.Pageable;
import com.vector.common.core.result.PageResult;
import com.vector.common.core.result.R;
import com.vector.pms.entity.PmsBrand;
import com.vector.pms.service.PmsBrandService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pms/brand")
public class PmsBrandController {

    @Autowired
    private PmsBrandService pmsBrandService;

    @GetMapping("/list")
    public R<PageResult> list(@RequestParam Map<String, Object> params) {
        PmsBrand query = Pageable.getQuery(params, PmsBrand.class);
        IPage<PmsBrand> page = pmsBrandService.page(Pageable.getPage(params), new LambdaQueryWrapper<PmsBrand>()
                .eq(StringUtils.isNotBlank(query.getName()), PmsBrand::getName, query.getName())
                .eq(StringUtils.isNotBlank(query.getInitial()), PmsBrand::getInitial, query.getInitial())
        );
        return R.page(page.getRecords(), page.getTotal());
    }

    @GetMapping("/{id}")
    public R<PmsBrand> get(@PathVariable Long id) {
        return R.ok(pmsBrandService.getById(id));
    }

    @PostMapping
    public R<?> add(@RequestBody PmsBrand pmsBrand) {
        pmsBrandService.save(pmsBrand);
        return R.ok();
    }

    @PutMapping
    public R<?> update(@RequestBody PmsBrand pmsBrand) {
        pmsBrandService.updateById(pmsBrand);
        return R.ok();
    }

    @DeleteMapping("/{ids}")
    public R<?> delete(@PathVariable List<Long> ids) {
        pmsBrandService.removeByIds(ids);
        return R.ok();
    }

}
