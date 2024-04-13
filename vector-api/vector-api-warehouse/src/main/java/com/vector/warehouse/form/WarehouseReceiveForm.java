package com.vector.warehouse.form;

import com.vector.warehouse.enums.BizType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 收货单表单
 */
@Data
public class WarehouseReceiveForm {

    /** 业务类型 */
    private BizType bizType;

    /** 业务单号 */
    private String bizNo;

    /** 收货明细 */
    private List<Detail> details;

    @Data
    public static class Detail {

        /** 产品ID */
        private Long productId;

        /** 应收数量 */
        private Integer qty;
    }

}
