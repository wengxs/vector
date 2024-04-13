package com.vector.warehouse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vector.warehouse.entity.WarehouseReceive;
import com.vector.warehouse.vo.WarehouseReceiveVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WarehouseReceiveMapper extends BaseMapper<WarehouseReceive> {

    WarehouseReceiveVo selectVoById(Long id);

    IPage<WarehouseReceiveVo> selectVoPage(@Param("page") IPage<?> page, @Param("q") WarehouseReceiveVo query);
}
