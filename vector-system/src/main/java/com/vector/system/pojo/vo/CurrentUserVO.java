package com.vector.system.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Set;

/**
 * 当前登录用户视图
 * @author wengxs
 */
@Schema(description = "当前登录用户视图")
@Data
public class CurrentUserVO {

    /** 用户名 */
    @Schema(description = "用户名")
    private String username;

    /** 昵称 */
    @Schema(description = "昵称")
    private String nickname;

    /** 用户头像 */
    @Schema(description = "用户头像")
    private String avatar;

    /** 手机号码 */
    @Schema(description = "手机号码")
    private String mobile;

    /** 角色 */
    @Schema(description = "角色集合")
    private Set<String> roles;

    /** 权限 */
    @Schema(description = "权限集合")
    private Set<String> permissions;
}
