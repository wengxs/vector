package com.vector.base.entity;

import com.vector.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Zorg
 * @date 2022/3/24
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseProduct extends BaseEntity<Integer> {

    /** 产品名称 */
    private String productName;

}
