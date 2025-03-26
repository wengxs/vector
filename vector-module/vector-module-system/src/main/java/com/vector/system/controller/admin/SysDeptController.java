package com.vector.system.controller.admin;

import com.vector.common.core.result.R;
import com.vector.system.dto.SysDeptDTO;
import com.vector.system.entity.SysDept;
import com.vector.system.query.SysDeptQuery;
import com.vector.system.service.SysDeptService;
import com.vector.system.vo.SysDeptTree;
import com.vector.system.vo.SysDeptVO;
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
@RestController
@RequestMapping("/sys/dept")
public class SysDeptController {

    @Autowired
    private SysDeptService sysDeptService;

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

    @GetMapping("/{id}")
    @PreAuthorize("@ss.hasAuthority('sys:dept:query')")
    public R<SysDeptVO> get(@PathVariable Long id) {
        return R.ok(sysDeptService.getVOById(id));
    }

    @PostMapping
    @PreAuthorize("@ss.hasAuthority('sys:dept:add')")
    public R<?> add(@RequestBody SysDeptDTO deptDTO) {
        SysDept sysDept = new SysDept();
        BeanUtils.copyProperties(deptDTO, sysDept);
        SysDept parent = sysDeptService.getById(deptDTO.getParentId());
        if (parent == null) {
            sysDept.setAncestors("0");
        } else {
            sysDept.setAncestors(parent.getAncestors() + "," + parent.getId());
        }
        sysDeptService.save(sysDept);
        return R.ok();
    }

    @PutMapping
    @PreAuthorize("@ss.hasAuthority('sys:dept:edit')")
    public R<?> update(@RequestBody SysDeptDTO deptDTO) {
        // TODO 更新下级的祖级
        SysDept sysDept = new SysDept();
        BeanUtils.copyProperties(deptDTO, sysDept);
        sysDeptService.updateById(sysDept);
        return R.ok();
    }

    @DeleteMapping("/{ids}")
    @PreAuthorize("@ss.hasAuthority('sys:dept:del')")
    public R<?> delete(@PathVariable List<Long> ids) {
        sysDeptService.removeByIds(ids);
        return R.ok();
    }

    @GetMapping("/tree")
    public R<List<SysDeptTree>> tree() {
        return R.ok(sysDeptService.deptTree());
    }

}
