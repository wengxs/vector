package com.vector.info.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.vector.common.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("info_supplier")
public class InfoSupplier extends BaseEntity<Long> {

    /** 供应商名称 */
    private String name;

    /** 联系人 */
    private String contacts;

    /** 联系电话 */
    private String mobile;

    /** 所在省份 */
    private String province;

    /** 详细地址 */
    private String address;
}
