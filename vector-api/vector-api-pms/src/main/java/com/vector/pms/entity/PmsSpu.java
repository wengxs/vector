package com.vector.pms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.vector.common.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 产品信息
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("pms_spu")
public class PmsSpu extends BaseEntity<Long> {

    /** 产品名称 */
    private String spuName;

    /** 默认图片 */
    private String defaultImage;

    /** 产品描述 */
    private String spuDescription;

    /** 品牌ID */
    private Long brandId;

    /** 分类ID */
    private Long categoryId;

    /** 商家ID */
    private Long sellerId;
}
