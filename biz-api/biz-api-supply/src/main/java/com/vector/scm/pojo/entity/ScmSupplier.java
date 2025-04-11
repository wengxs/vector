package com.vector.scm.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.vector.common.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 供应商信息 Entity
 * @author wengxs
 * @date 2024/05/12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("scm_supplier")
public class ScmSupplier extends BaseEntity<Long> {

    /** 供应商 */
    private String supplierName;

    /** 联系人 */
    private String contacts;

    /** 联系电话 */
    private String mobile;

    /** 所在省份 */
    private String province;

    /** 详细地址 */
    private String address;
}
