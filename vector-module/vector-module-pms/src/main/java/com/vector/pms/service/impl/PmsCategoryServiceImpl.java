package com.vector.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vector.pms.entity.PmsCategory;
import com.vector.pms.mapper.PmsCategoryMapper;
import com.vector.pms.service.PmsCategoryService;
import com.vector.pms.vo.PmsCategoryTreeVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Zorg
 * 2020/5/16 01:25
 */
@Service
public class PmsCategoryServiceImpl extends ServiceImpl<PmsCategoryMapper, PmsCategory> implements PmsCategoryService {

    @Override
    public List<PmsCategoryTreeVo> tree(PmsCategoryTreeVo pmsCategoryTreeVo) {
        List<PmsCategory> list = baseMapper.selectList(new LambdaQueryWrapper<>(PmsCategory.class)
                .orderByAsc(PmsCategory::getSort, PmsCategory::getIcon)
        );
        return getChildrenTreeVo(list, 0L);
    }

    private List<PmsCategoryTreeVo> getChildrenTreeVo(List<PmsCategory> categories, Long parentId) {
        return categories.stream()
                .filter(pmsCategory -> pmsCategory.getParentId().equals(parentId))
                .map(pmsCategory -> {
                    PmsCategoryTreeVo treeVo = new PmsCategoryTreeVo();
                    treeVo.setId(pmsCategory.getId());
                    treeVo.setCreateTime(pmsCategory.getCreateTime());
                    treeVo.setCreateBy(pmsCategory.getCreateBy());
                    treeVo.setUpdateTime(pmsCategory.getUpdateTime());
                    treeVo.setUpdateBy(pmsCategory.getUpdateBy());
                    treeVo.setName(pmsCategory.getName());
                    treeVo.setIcon(pmsCategory.getIcon());
                    treeVo.setParentId(pmsCategory.getParentId());
                    treeVo.setLevel(pmsCategory.getLevel());
                    treeVo.setSort(pmsCategory.getSort());
                    treeVo.setShowStatus(pmsCategory.getShowStatus());
                    treeVo.setChildren(getChildrenTreeVo(categories, pmsCategory.getId()));
                    return treeVo;
                })
                .collect(Collectors.toList());
    }
}
