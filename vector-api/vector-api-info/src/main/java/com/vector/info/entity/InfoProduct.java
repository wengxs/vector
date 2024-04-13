package com.vector.info.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.vector.common.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("info_product")
public class InfoProduct extends BaseEntity<Long> {

    private String name;

    private String factory;
}
