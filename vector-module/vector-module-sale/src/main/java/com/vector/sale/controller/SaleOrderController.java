package com.vector.sale.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vector.common.core.pagination.Pageable;
import com.vector.common.core.result.PageResult;
import com.vector.common.core.result.R;
import com.vector.sale.form.SaleOrderForm;
import com.vector.sale.service.SaleOrderService;
import com.vector.sale.vo.SaleOrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/sale/order")
public class SaleOrderController {

    @Autowired
    private SaleOrderService saleOrderService;

    @GetMapping("/list")
    public R<PageResult> list(@RequestParam Map<String, Object> params) {
        SaleOrderVo query = Pageable.getQuery(params, SaleOrderVo.class);
        IPage<SaleOrderVo> page = saleOrderService.pageVo(Pageable.getPage(params), query);
        return R.page(page.getRecords(), page.getTotal());
    }

    @GetMapping("/{id}")
    public R<SaleOrderVo> get(@PathVariable Long id) {
        return R.ok(saleOrderService.getVoById(id));
    }

    @PostMapping
    public R<?> add(@RequestBody SaleOrderForm saleOrderForm) {
        saleOrderService.createOrder(saleOrderForm);
        return R.ok();
    }

//    @PutMapping
//    public R<?> update(@RequestBody SaleOrderForm saleOrderForm) {
//        saleOrderService.updateById(saleOrderForm);
//        return R.ok();
//    }

    /**
     * 取消订单
     * @param id
     * @return
     */
    @PutMapping("/{id}/cancel")
    public R<?> cancel(@PathVariable Long id) {
        saleOrderService.cancel(id);
        return R.ok();
    }

    @PutMapping("/{id}/pay")
    public R<?> pay(@PathVariable Long id) {
        saleOrderService.pay(id);
        return R.ok();
    }
}
