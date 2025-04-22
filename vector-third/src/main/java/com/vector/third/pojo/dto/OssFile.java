package com.vector.third.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * OSS文件
 * @author wengxs
 * @date 2024/5/5
 */
@Schema(description = "OSS文件对象")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OssFile {

    @Schema(description = "文件名称")
    private String fileName;

    @Schema(description = "文件URL")
    private String url;
}
