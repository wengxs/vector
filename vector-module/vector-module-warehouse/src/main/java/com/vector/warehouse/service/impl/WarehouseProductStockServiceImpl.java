package com.vector.warehouse.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vector.common.core.util.BizAssert;
import com.vector.warehouse.constant.WarehouseConstant;
import com.vector.warehouse.entity.WarehouseProductStock;
import com.vector.warehouse.form.WarehouseProductStockLockForm;
import com.vector.warehouse.mapper.WarehouseProductStockMapper;
import com.vector.warehouse.service.WarehouseProductStockService;
import com.vector.warehouse.vo.WarehouseProductStockVo;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
public class WarehouseProductStockServiceImpl extends ServiceImpl<WarehouseProductStockMapper, WarehouseProductStock> 
        implements WarehouseProductStockService {

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public WarehouseProductStockVo getVoById(Long id) {
        return baseMapper.selectVoById(id);
    }

    @Override
    public IPage<WarehouseProductStockVo> pageVo(IPage<?> page, WarehouseProductStockVo query) {
        return baseMapper.selectVoPage(page, query);
    }

    @Override
    @Transactional
    public void lock(WarehouseProductStockLockForm lockForm) {
        for (WarehouseProductStockLockForm.Detail detail : lockForm.getDetails()) {
            this.lock(detail.getProductId(), detail.getQty());
        }
    }

    @Override
    @Transactional
    public void lock(Long productId, Integer qty) {
        RLock lock = redissonClient.getLock(WarehouseConstant.LOCK_PREFIX_PRODUCT_STOCK + productId);
        lock.lock(3, TimeUnit.SECONDS);
        try {
            int res = baseMapper.lock(productId, qty);
            BizAssert.isTrue(res > 0, "商品库存不足");
        } finally {
            lock.unlock();
        }
    }

    @Override
    @Transactional
    public void unlock(WarehouseProductStockLockForm lockForm) {
        for (WarehouseProductStockLockForm.Detail detail : lockForm.getDetails()) {
            this.unlock(detail.getProductId(), detail.getQty());
        }
    }

    @Override
    @Transactional
    public void unlock(Long productId, Integer qty) {
        RLock lock = redissonClient.getLock(WarehouseConstant.LOCK_PREFIX_PRODUCT_STOCK + productId);
        lock.lock(3, TimeUnit.SECONDS);
        try {
            int res = baseMapper.unlock(productId, qty);
            BizAssert.isTrue(res > 0, "商品库存释放失败");
        } finally {
            lock.unlock();
        }
    }

}
