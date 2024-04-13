package com.vector.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vector.pms.entity.PmsCategory;
import com.vector.pms.vo.PmsCategoryTreeVo;

import java.util.List;

public interface PmsCategoryService extends IService<PmsCategory> {
    List<PmsCategoryTreeVo> tree(PmsCategoryTreeVo pmsCategoryTreeVo);

}
