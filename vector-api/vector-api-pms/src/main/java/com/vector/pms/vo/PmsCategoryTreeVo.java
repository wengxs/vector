package com.vector.pms.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 分类树结构
 */
@Data
public class PmsCategoryTreeVo {

    /** 分类ID */
    private Long id;

    /** 分类名称 */
    private String name;

    /** 图标 */
    private String icon;

    /** 父级ID */
    private Long parentId;

    /** 层级 */
    private Integer level;

    /** 排序 */
    private Integer sort;

    /** 展示状态(0=不展示,1=展示) */
    private Integer showStatus;

    /**创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 创建人 */
    private String createBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /** 更新人 */
    private String updateBy;

    private List<PmsCategoryTreeVo> children;

}
