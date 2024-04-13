package com.vector.info.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vector.common.core.pagination.Pageable;
import com.vector.common.core.result.PageResult;
import com.vector.common.core.result.R;
import com.vector.info.entity.InfoSupplier;
import com.vector.info.service.InfoSupplierService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/info/supplier")
public class InfoSupplierController {

    @Autowired
    private InfoSupplierService infoSupplierService;

    @GetMapping("/list")
    public R<PageResult> list(@RequestParam Map<String, Object> params) {
        InfoSupplier query = Pageable.getQuery(params, InfoSupplier.class);
        IPage<InfoSupplier> page = infoSupplierService.page(Pageable.getPage(params), new LambdaQueryWrapper<>(InfoSupplier.class)
                .like(StringUtils.isNotBlank(query.getName()), InfoSupplier::getName, query.getName())
                .like(StringUtils.isNotBlank(query.getProvince()), InfoSupplier::getProvince, query.getProvince())
        );
        return R.page(page.getRecords(), page.getTotal());
    }

    @GetMapping("/{id}")
    public R<InfoSupplier> get(@PathVariable Long id) {
        return R.ok(infoSupplierService.getById(id));
    }

    @PostMapping
    public R<?> add(@RequestBody InfoSupplier infoSupplier) {
        infoSupplierService.save(infoSupplier);
        return R.ok();
    }

    @PutMapping
    public R<?> update(@RequestBody InfoSupplier infoSupplier) {
        infoSupplierService.updateById(infoSupplier);
        return R.ok();
    }

    @DeleteMapping("/{ids}")
    public R<?> delete(@PathVariable List<Long> ids) {
        infoSupplierService.removeByIds(ids);
        return R.ok();
    }
}
