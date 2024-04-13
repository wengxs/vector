package com.vector.warehouse.service.impl;

import com.vector.warehouse.WarehouseApplication;
import com.vector.warehouse.form.WarehouseProductStockLockForm;
import com.vector.warehouse.service.WarehouseProductStockService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WarehouseApplication.class)
class WarehouseProductStockServiceImplTest {

    @Autowired
    private WarehouseProductStockService warehouseProductStockService;

    @Test
    void lock() throws InterruptedException {
        WarehouseProductStockLockForm lockForm = new WarehouseProductStockLockForm();
        WarehouseProductStockLockForm.Detail detail = new WarehouseProductStockLockForm.Detail();
        detail.setProductId(2L);
        detail.setQty(2);
        WarehouseProductStockLockForm.Detail detail2 = new WarehouseProductStockLockForm.Detail();
        detail2.setProductId(2L);
        detail2.setQty(2);
        lockForm.setDetails(Arrays.asList(detail, detail2));
        warehouseProductStockService.lock(lockForm);
        Thread thread1 = new Thread(() -> warehouseProductStockService.lock(lockForm), "A");
        Thread thread2 = new Thread(() -> warehouseProductStockService.lock(lockForm), "B");
        Thread thread3 = new Thread(() -> warehouseProductStockService.lock(lockForm), "C");
        Thread thread4 = new Thread(() -> warehouseProductStockService.lock(lockForm), "D");
//        thread1.
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
//        warehouseProductStockService.lock(2L, 1);
        TimeUnit.SECONDS.sleep(20);
    }

}