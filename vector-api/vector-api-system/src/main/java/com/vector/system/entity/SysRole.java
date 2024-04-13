package com.vector.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.vector.common.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zorg
 * 2020/5/15 23:55
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role")
public class SysRole extends BaseEntity<Long> {

    /** 角色名称 */
    private String name;

    /** 角色标识 */
    private String roleKey;

    /** 角色描述 */
    private String description;

}
