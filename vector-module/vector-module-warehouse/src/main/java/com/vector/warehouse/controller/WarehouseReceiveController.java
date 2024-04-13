package com.vector.warehouse.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vector.common.core.pagination.Pageable;
import com.vector.common.core.result.PageResult;
import com.vector.common.core.result.R;
import com.vector.warehouse.form.WarehouseCheckForm;
import com.vector.warehouse.form.WarehouseReceiveForm;
import com.vector.warehouse.service.WarehouseReceiveService;
import com.vector.warehouse.vo.WarehouseReceiveVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/warehouse/receive")
public class WarehouseReceiveController {

    @Autowired
    private WarehouseReceiveService warehouseReceiveService;

    @GetMapping("/list")
    public R<PageResult> list(@RequestParam Map<String, Object> params) {
        WarehouseReceiveVo query = Pageable.getQuery(params, WarehouseReceiveVo.class);
        IPage<WarehouseReceiveVo> page = warehouseReceiveService.pageVo(Pageable.getPage(params), query);
        return R.page(page.getRecords(), page.getTotal());
    }

    @GetMapping("/{id}")
    public R<WarehouseReceiveVo> get(@PathVariable Long id) {
        return R.ok(warehouseReceiveService.getVoById(id));
    }

    @PostMapping()
    public R<?> add(@RequestBody WarehouseReceiveForm warehouseReceiveForm) {
        warehouseReceiveService.create(warehouseReceiveForm);
        return R.ok();
    }

    @PutMapping("/{id}/sign")
    public R<?> sign(@PathVariable Long id) {
        warehouseReceiveService.sign(id);
        return R.ok();
    }

    @PutMapping("/check")
    public R<?> check(@RequestBody WarehouseCheckForm warehouseCheckForm) {
        warehouseReceiveService.check(warehouseCheckForm);
        return R.ok();
    }

}
