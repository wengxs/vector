package com.vector.system.pojo.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author wengxs
 * @date 2024/5/12
 */
@Schema(description = "当前用户表单对象")
@Data
public class SysCurrentForm {

    /** 手机号码 */
    @Schema(description = "手机号码")
    private String mobile;

    /** 头像 */
    @Schema(description = "用户头像")
    private String avatar;

    /** 旧密码 */
    @Schema(description = "旧密码", required = true)
    private String oldPassword;

    /** 新密码 */
    @Schema(description = "新密码", required = true)
    private String newPassword;
} 