package com.vector.warehouse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vector.warehouse.entity.WarehouseReceiveDetail;
import com.vector.warehouse.vo.WarehouseReceiveVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WarehouseReceiveDetailMapper extends BaseMapper<WarehouseReceiveDetail> {

    List<WarehouseReceiveVo.Detail> selectVoByReceiveId(Long receiveId);
}
