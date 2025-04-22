package com.vector.system.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 前端路由视图
 */
@Schema(description = "路由视图")
@Data
public class RouterVO {

    @Schema(description = "路由名称")
    private String name;

    @Schema(description = "路径")
    private String path;

    @Schema(description = "组件")
    private String component;

    @Schema(description = "Meta对象")
    private Meta meta;

    @Schema(description = "子路由集合")
    private List<RouterVO> children;

    @Schema(description = "Meta")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Meta {

        @Schema(description = "标题")
        private String title;

        @Schema(description = "图标")
        private String icon;

        @Schema(description = "是否隐藏")
        private Boolean hidden;

        @Schema(description = "是否缓存组件")
        private Boolean keepAlive;
    }

}
