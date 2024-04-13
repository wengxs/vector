package com.vector.pms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.vector.common.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 产品图片
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("pms_spu_image")
public class PmsSpuImage extends BaseEntity<Long> {

    /** 产品ID */
    private Long spuId;

    /** 图片地址 */
    private String imgUrl;

    /** 排序 */
    private Integer sort;

    /** 默认图片(0=否,1=是) */
    private Integer defaultImg;

}
