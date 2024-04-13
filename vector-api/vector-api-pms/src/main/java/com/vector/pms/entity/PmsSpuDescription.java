package com.vector.pms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 产品介绍
 */
@Data
@TableName("pms_spu_description")
public class PmsSpuDescription {

    /** 产品ID */
    private Long spuId;

    /** 产品介绍 */
    private String description;

}
