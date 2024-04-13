package com.vector.info.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.vector.common.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 地区信息
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("info_area")
public class InfoArea extends BaseEntity<Long> {

    /** 地区编码 */
    private String code;

    /** 地区名称 */
    private String name;

    /** 地区全称 */
    private String fullName;

    /** 地区等级 */
    private Integer level;

    /** 上级编码 */
    private String parentCode;
}
