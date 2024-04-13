package com.vector.pms.controller.admin;

import com.vector.common.core.result.R;
import com.vector.pms.entity.PmsCategory;
import com.vector.pms.service.PmsCategoryService;
import com.vector.pms.vo.PmsCategoryTreeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pms/category")
public class PmsCategoryController {

    @Autowired
    private PmsCategoryService pmsCategoryService;

    @GetMapping("/tree")
    public R<List<PmsCategoryTreeVo>> tree(PmsCategoryTreeVo params) {
        return R.ok(pmsCategoryService.tree(params));
    }

    @GetMapping("{id}")
    public R<PmsCategory> get(@PathVariable Long id) {
        return R.ok(pmsCategoryService.getById(id));
    }

    @PostMapping
    public R<?> add(@RequestBody PmsCategory pmsCategory) {
        if (pmsCategory.getParentId() != 0) {
            PmsCategory parent = pmsCategoryService.getById(pmsCategory.getParentId());
            pmsCategory.setLevel(parent.getLevel() + 1);
        } else {
            pmsCategory.setLevel(1);
        }
        pmsCategoryService.save(pmsCategory);
        return R.ok();
    }

    @PutMapping
    public R<?> update(@RequestBody PmsCategory pmsCategory) {
        if (pmsCategory.getParentId() != 0) {
            PmsCategory parent = pmsCategoryService.getById(pmsCategory.getParentId());
            pmsCategory.setLevel(parent.getLevel() + 1);
        } else {
            pmsCategory.setLevel(1);
        }
        pmsCategoryService.updateById(pmsCategory);
        return R.ok();
    }

    @DeleteMapping("/{ids}")
    public R<?> delete(@PathVariable List<Long> ids) {
        pmsCategoryService.removeByIds(ids);
        return R.ok();
    }

}
