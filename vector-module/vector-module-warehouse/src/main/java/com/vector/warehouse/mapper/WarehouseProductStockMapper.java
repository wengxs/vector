package com.vector.warehouse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vector.warehouse.entity.WarehouseProductStock;
import com.vector.warehouse.vo.WarehouseProductStockVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface WarehouseProductStockMapper extends BaseMapper<WarehouseProductStock> {

    WarehouseProductStockVo selectVoById(Long id);

    IPage<WarehouseProductStockVo> selectVoPage(@Param("page") IPage<?> page, @Param("q") WarehouseProductStockVo query);

    @Update("update warehouse_product_stock set usable_stock=usable_stock-#{qty}, locked_stock=locked_stock+#{qty} " +
            "where product_id=#{productId} and usable_stock>=#{qty} ")
    int lock(@Param("productId") Long productId, @Param("qty") Integer qty);

    @Update("update warehouse_product_stock set usable_stock=usable_stock+#{qty}, locked_stock=locked_stock-#{qty} " +
            "where product_id=#{productId} and locked_stock>=#{qty} ")
    int unlock(@Param("productId") Long productId, @Param("qty") Integer qty);
}
