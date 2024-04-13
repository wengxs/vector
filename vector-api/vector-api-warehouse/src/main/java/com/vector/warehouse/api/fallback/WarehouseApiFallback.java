package com.vector.warehouse.api.fallback;

import com.vector.common.core.result.R;
import com.vector.warehouse.api.WarehouseApi;
import com.vector.warehouse.form.WarehouseProductStockLockForm;
import com.vector.warehouse.form.WarehouseReceiveForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WarehouseApiFallback implements FallbackFactory<WarehouseApi> {

    @Override
    public WarehouseApi create(Throwable cause) {
        return new WarehouseApi() {
            @Override
            public R<?> receiveCreate(WarehouseReceiveForm warehouseReceiveForm) {
                log.error("WarehouseApi:receiveCreate调用失败");
                return R.fail("WarehouseApi:receiveCreate调用失败");
            }

            @Override
            public R<?> productStockLock(WarehouseProductStockLockForm lockForm) {
                log.error("WarehouseApi:productStockLock调用失败");
                return R.fail("WarehouseApi:productStockLock调用失败");
            }

            @Override
            public R<?> productStockUnlock(WarehouseProductStockLockForm lockForm) {
                log.error("WarehouseApi:productStockUnlock调用失败");
                return R.fail("WarehouseApi:productStockUnlock调用失败");
            }
        };
    }
}
