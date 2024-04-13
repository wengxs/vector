package com.vector.warehouse.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.vector.common.core.entity.BaseEntity;
import com.vector.warehouse.enums.BizType;
import com.vector.warehouse.enums.WarehouseReceiveStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 收货单
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("warehouse_receive")
public class WarehouseReceive extends BaseEntity<Long> {

    /** 收货单号 */
    private String receiveNo;

    /** 收货状态 */
    private WarehouseReceiveStatus receiveStatus;

    /** 业务类型 */
    private BizType bizType;

    /** 业务单号 */
    private String bizNo;

    /** 物流名称 */
    private String logisticsName;

    /** 物流单号 */
    private String logisticsNo;

    /** 签收时间 */
    private Date signedTime;

    /** 批次编号 */
    private String batchNo;

}
