package com.vector.warehouse.api;

import com.vector.common.core.result.R;
import com.vector.warehouse.api.fallback.WarehouseApiFallback;
import com.vector.warehouse.form.WarehouseProductStockLockForm;
import com.vector.warehouse.form.WarehouseReceiveForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "warehouse-service", fallbackFactory = WarehouseApiFallback.class)
public interface WarehouseApi {

    @PostMapping("/warehouse/receive")
    R<?> receiveCreate(@RequestBody WarehouseReceiveForm warehouseReceiveForm);

    @PostMapping("/warehouse/productStock/lock")
    R<?> productStockLock(@RequestBody WarehouseProductStockLockForm lockForm);

    @PostMapping("/warehouse/productStock/unlock")
    R<?> productStockUnlock(WarehouseProductStockLockForm lockForm);
}
