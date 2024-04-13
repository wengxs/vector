package com.vector.purchase.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vector.common.core.pagination.Pageable;
import com.vector.common.core.result.PageResult;
import com.vector.common.core.result.R;
import com.vector.purchase.entity.PurchaseOrder;
import com.vector.purchase.form.PurchaseOrderForm;
import com.vector.purchase.service.PurchaseOrderService;
import com.vector.purchase.vo.PurchaseOrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/purchase/order")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @GetMapping("/list")
    public R<PageResult> list(@RequestParam Map<String, Object> params) {
        PurchaseOrderVo query = Pageable.getQuery(params, PurchaseOrderVo.class);
        IPage<PurchaseOrderVo> page = purchaseOrderService.pageVo(Pageable.getPage(params), query);
        return R.page(page.getRecords(), page.getTotal());
    }

    @GetMapping("/{id}")
    public R<PurchaseOrderVo> get(@PathVariable Long id) {
        return R.ok(purchaseOrderService.getVoById(id));
    }

    @PostMapping
    public R<?> add(@RequestBody PurchaseOrderForm purchaseOrderForm) {
        purchaseOrderService.createOrder(purchaseOrderForm);
        return R.ok();
    }

    @PutMapping
    public R<?> update(@RequestBody PurchaseOrderForm purchaseOrderForm) {
        purchaseOrderService.updateById(purchaseOrderForm);
        return R.ok();
    }

    /**
     * 取消订单
     * @param id
     * @return
     */
    @PutMapping("/{id}/cancel")
    public R<?> cancel(@PathVariable Long id) {
        purchaseOrderService.cancel(id);
        return R.ok();
    }

    /**
     * 签约采购
     * @param id
     * @return
     */
    @PutMapping("/{id}/sign")
    public R<?> sign(@PathVariable Long id) {
        purchaseOrderService.sign(id);
        return R.ok();
    }

    /**
     * 发货信息回传
     * @param id
     * @return
     */
    @PutMapping("/{id}/send")
    public R<?> send(@PathVariable Long id, @RequestBody Map<String, String> params) {
        String logisticsName = params.get("logisticsName");
        String logisticsNo = params.get("logisticsNo");
        purchaseOrderService.callbackLogistics(id, logisticsName, logisticsNo);
        return R.ok();
    }
}
