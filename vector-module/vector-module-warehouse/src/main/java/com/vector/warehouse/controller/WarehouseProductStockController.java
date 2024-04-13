package com.vector.warehouse.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vector.common.core.pagination.Pageable;
import com.vector.common.core.result.PageResult;
import com.vector.common.core.result.R;
import com.vector.warehouse.form.WarehouseProductStockLockForm;
import com.vector.warehouse.service.WarehouseProductStockService;
import com.vector.warehouse.service.WarehouseProductStockService;
import com.vector.warehouse.vo.WarehouseProductStockVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/warehouse/productStock")
public class WarehouseProductStockController {

    @Autowired
    private WarehouseProductStockService warehouseProductStockService;

    @GetMapping("/list")
    public R<PageResult> list(@RequestParam Map<String, Object> params) {
        WarehouseProductStockVo query = Pageable.getQuery(params, WarehouseProductStockVo.class);
        IPage<WarehouseProductStockVo> page = warehouseProductStockService.pageVo(Pageable.getPage(params), query);
        return R.page(page.getRecords(), page.getTotal());
    }

    @GetMapping("/{id}")
    public R<WarehouseProductStockVo> get(@PathVariable Long id) {
        return R.ok(warehouseProductStockService.getVoById(id));
    }

    @PostMapping("/lock")
    public R<?> lock(@RequestBody WarehouseProductStockLockForm lockForm) {
        warehouseProductStockService.lock(lockForm);
        return R.ok();
    }

    @PostMapping("/unlock")
    public R<?> unlock(@RequestBody WarehouseProductStockLockForm lockForm) {
        warehouseProductStockService.unlock(lockForm);
        return R.ok();
    }

}
