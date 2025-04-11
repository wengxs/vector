package com.vector.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vector.pms.pojo.entity.PmsCategory;
import com.vector.pms.pojo.vo.PmsCategoryTreeVO;

import java.util.List;

public interface PmsCategoryService extends IService<PmsCategory> {

    List<PmsCategoryTreeVO> tree();
}
