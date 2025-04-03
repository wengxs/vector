package com.vector.third.controller;

import com.vector.common.core.result.R;
import com.vector.third.pojo.dto.OssFile;
import com.vector.third.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件服务
 * @author wengxs
 * @date 2024/5/5
 */
@RestController
@RequestMapping("/third/file")
public class FileController {

    @Autowired
    private OssService ossService;

    @PostMapping()
    public R<OssFile> uploadFile(@RequestParam(value = "file") MultipartFile file) {
        return R.ok(ossService.uploadFile(file));
    }

    @DeleteMapping
    public R<?> deleteFile(@RequestParam String filePath) {
        ossService.removeFile(filePath);
        return R.ok();
    }
}
