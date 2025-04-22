package com.vector.system.controller.admin;

import com.vector.common.core.result.R;
import com.vector.system.pojo.entity.SysDept;
import com.vector.system.pojo.form.SysDeptForm;
import com.vector.system.pojo.query.SysDeptQuery;
import com.vector.system.pojo.vo.SysDeptTree;
import com.vector.system.pojo.vo.SysDeptVO;
import com.vector.system.service.SysDeptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门 Controller
 * @author wengxs
 */
@Tag(name = "系统部门")
@RestController
@RequestMapping("/sys/dept")
public class SysDeptController {

    @Autowired
    private SysDeptService sysDeptService;

    @Operation(summary = "部门列表")
    @GetMapping("/list")
    @PreAuthorize("@ss.hasAuthority('sys:dept:query')")
    public R<List<SysDeptVO>> list(SysDeptQuery query) {
        List<SysDeptVO> list = sysDeptService.listTree();
        return R.ok(filterQuery(list, query));
    }

    private List<SysDeptVO> filterQuery(List<SysDeptVO> list, SysDeptQuery query) {
        return list.stream().filter(item -> {
            int queryNum = 0;
            if (StringUtils.isNotBlank(query.getDeptName())) {
                queryNum++;
                if (item.getDeptName().contains(query.getDeptName())) {
                    queryNum--;
                }
            }
            if (StringUtils.isNotBlank(query.getDeptStatus())) {
                queryNum++;
                if (item.getDeptStatus().equals(query.getDeptStatus())){
                    queryNum--;
                }
            }
            if (queryNum == 0) {
                return true;
            }
            item.setChildren(filterQuery(item.getChildren(), query));
            return !CollectionUtils.isEmpty(item.getChildren());
        }).toList();
    }

    @Operation(summary = "获取部门")
    @GetMapping("/{id}")
    @PreAuthorize("@ss.hasAuthority('sys:dept:query')")
    public R<SysDeptVO> get(@Parameter(description = "部门ID") @PathVariable Long id) {
        return R.ok(sysDeptService.getVOById(id));
    }

    @Operation(summary = "新增部门")
    @PostMapping
    @PreAuthorize("@ss.hasAuthority('sys:dept:add')")
    public R<?> add(@RequestBody SysDeptForm deptForm) {
        SysDept sysDept = new SysDept();
        BeanUtils.copyProperties(deptForm, sysDept);
        SysDept parent = sysDeptService.getById(deptForm.getParentId());
        if (parent == null) {
            sysDept.setAncestors("0");
        } else {
            sysDept.setAncestors(parent.getAncestors() + "," + parent.getId());
        }
        sysDeptService.save(sysDept);
        return R.ok();
    }

    @Operation(summary = "修改部门")
    @PutMapping
    @PreAuthorize("@ss.hasAuthority('sys:dept:edit')")
    public R<?> update(@RequestBody SysDeptForm deptForm) {
        // 更新下级的祖级
        SysDept sysDept = new SysDept();
        BeanUtils.copyProperties(deptForm, sysDept);
        sysDeptService.updateById(sysDept);
        return R.ok();
    }

    @Operation(summary = "删除部门")
    @DeleteMapping("/{ids}")
    @PreAuthorize("@ss.hasAuthority('sys:dept:del')")
    public R<?> delete(@Parameter(description = "部门ID数组") @PathVariable List<Long> ids) {
        sysDeptService.removeByIds(ids);
        return R.ok();
    }

    @Operation(summary = "获取部门树")
    @GetMapping("/tree")
    public R<List<SysDeptTree>> tree() {
        return R.ok(sysDeptService.deptTree());
    }

}
