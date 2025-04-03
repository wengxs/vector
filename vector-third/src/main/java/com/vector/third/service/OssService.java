package com.vector.third.service;

import com.vector.third.pojo.dto.OssFile;
import org.springframework.web.multipart.MultipartFile;

/**
 * Oss对象存储接口
 *
 * @author wengxs
 */
public interface OssService {

    /**
     * 上传文件
     * @param file 文件
     * @return
     */
    OssFile uploadFile(MultipartFile file);

    /**
     * 删除文件
     * @param filePath 文件路径
     * @return
     */
    boolean removeFile(String filePath);
}
