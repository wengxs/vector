package com.vector.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.vector.common.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统用户表
 *
 * Created by Zorg
 * 2020-03-19 11:09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
public class SysUser extends BaseEntity<Long> {

    /** 用户名 */
    private String username;

    /** 密码 */
    private String password;
//
//    /** 盐加密 */
//    private String salt;

    /** 用户头像 */
    private String avatar;

    /** 帐号状态（0停用，1正常） */
    private Boolean enabled;

    /** 部门ID */
    private Long deptId;

}
