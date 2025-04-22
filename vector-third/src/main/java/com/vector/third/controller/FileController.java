package com.vector.third.controller;

import com.vector.common.core.result.R;
import com.vector.third.pojo.dto.OssFile;
import com.vector.third.service.OssService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件服务
 * @author wengxs
 * @date 2024/5/5
 */
@Tag(name = "文件服务")
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private OssService ossService;

    @Operation(summary = "上传文件")
    @PostMapping()
    public R<OssFile> uploadFile(@Parameter(description = "文件") @RequestParam(value = "file") MultipartFile file) {
        return R.ok(ossService.uploadFile(file));
    }

    @Operation(summary = "删除文件")
    @DeleteMapping
    public R<?> deleteFile(@Parameter(description = "文件路径") @RequestParam String filePath) {
        ossService.removeFile(filePath);
        return R.ok();
    }
}
