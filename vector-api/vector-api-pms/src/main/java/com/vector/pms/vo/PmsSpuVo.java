package com.vector.pms.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class PmsSpuVo  {

    /** ID */
    private Long id;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 创建人 */
    private String createBy;

    /** 产品名称 */
    private String spuName;

    /** 默认图片 */
    private String defaultImage;

    /** 产品描述 */
    private String spuDescription;

    /** 品牌ID */
    private Long brandId;

    /** 品牌 */
    private String brandName;

    /** 分类ID */
    private Long categoryId;

    /** 分类 */
    private String categoryName;

    /** 商家ID */
    private Long sellerId;
}
