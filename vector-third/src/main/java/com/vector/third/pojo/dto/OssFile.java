package com.vector.third.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * OSS文件
 * @author wengxs
 * @date 2024/5/5
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OssFile {

    private String fileName;

    private String url;
}
