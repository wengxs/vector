package com.vector.system.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vector.common.core.pagination.Pageable;
import com.vector.common.core.result.PageResult;
import com.vector.common.core.result.R;
import com.vector.system.pojo.entity.SysDict;
import com.vector.system.pojo.entity.SysDictOption;
import com.vector.system.pojo.form.SysDictForm;
import com.vector.system.pojo.form.SysDictOptionForm;
import com.vector.system.pojo.query.SysDictQuery;
import com.vector.system.pojo.vo.SysDictOptionVO;
import com.vector.system.pojo.vo.SysDictVO;
import com.vector.system.service.SysDictOptionService;
import com.vector.system.service.SysDictService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典 Controller
 * @author wengxs
 */
@Tag(name = "系统字典")
@RestController
@RequestMapping("/sys/dict")
public class SysDictController {

    @Autowired
    private SysDictService sysDictService;
    @Autowired
    private SysDictOptionService sysDictOptionService;

    @Operation(summary = "字典分页列表")
    @GetMapping("/list")
    @PreAuthorize("@ss.hasAuthority('sys:dict:query')")
    public R<PageResult> list(SysDictQuery query) {
        IPage<SysDictVO> page = sysDictService.pageVO(Pageable.getPage(query), query);
        return R.page(page.getRecords(), page.getTotal());
    }

    @Operation(summary = "获取字典")
    @GetMapping("/{id}")
    @PreAuthorize("@ss.hasAuthority('sys:dict:query')")
    public R<SysDictVO> get(@Parameter(description = "字典ID") @PathVariable Long id) {
        return R.ok(sysDictService.getVOById(id));
    }

    @Operation(summary = "新增字典")
    @PostMapping
    @PreAuthorize("@ss.hasAuthority('sys:dict:add')")
    public R<?> add(@RequestBody SysDictForm dictForm) {
        SysDict sysDict = new SysDict();
        BeanUtils.copyProperties(dictForm, sysDict);
        sysDictService.save(sysDict);
        return R.ok();
    }

    @Operation(summary = "修改字典")
    @PutMapping
    @PreAuthorize("@ss.hasAuthority('sys:dict:edit')")
    public R<?> update(@RequestBody SysDictForm dictForm) {
        SysDict sysDict = new SysDict();
        BeanUtils.copyProperties(dictForm, sysDict);
        sysDictService.updateById(sysDict);
        return R.ok();
    }

    @Operation(summary = "删除字典")
    @DeleteMapping("/{ids}")
    @PreAuthorize("@ss.hasAuthority('sys:dict:del')")
    public R<?> delete(@Parameter(description = "字典ID数组") @PathVariable List<Long> ids) {
        sysDictService.removeByIds(ids);
        return R.ok();
    }

    @Operation(summary = "获取字典选项列表")
    @GetMapping("/option/list")
    @PreAuthorize("@ss.hasAuthority('sys:dict:query')")
    public R<List<SysDictOption>> listOption(
            @Parameter(description = "字典键") @RequestParam String dictKey,
            @Parameter(description = "是否只返回启用的选项") @RequestParam(required = false, defaultValue = "false") Boolean onlyEnabled) {
        List<SysDictOption> list = sysDictOptionService.list(new LambdaQueryWrapper<SysDictOption>()
                .eq(SysDictOption::getDictKey, dictKey)
                .eq(onlyEnabled, SysDictOption::getOptionStatus, SysDictOption.OPTION_STATUS_ENABLED)
                .orderByAsc(SysDictOption::getSort));
        return R.ok(list);
    }

    @Operation(summary = "获取字典选项")
    @GetMapping("/option/{id}")
    @PreAuthorize("@ss.hasAuthority('sys:dict:query')")
    public R<SysDictOptionVO> getOption(@Parameter(description = "选项ID") @PathVariable String id) {
        SysDictOption dictOption = sysDictOptionService.getById(id);
        SysDictOptionVO vo = new SysDictOptionVO();
        BeanUtils.copyProperties(dictOption, vo);
        vo.setId(dictOption.getId());
        return R.ok(vo);
    }

    @Operation(summary = "新增字典选项")
    @PostMapping("/option")
    @PreAuthorize("@ss.hasAuthority('sys:dict:add')")
    public R<?> addOption(@RequestBody SysDictOptionForm dictOptionForm) {
        SysDictOption sysDictOption = new SysDictOption();
        BeanUtils.copyProperties(dictOptionForm, sysDictOption);
        sysDictOptionService.save(sysDictOption);
        return R.ok();
    }

    @Operation(summary = "修改字典选项")
    @PutMapping("/option")
    @PreAuthorize("@ss.hasAuthority('sys:dict:edit')")
    public R<?> updateOption(@RequestBody SysDictOptionForm dictOptionForm) {
        SysDictOption sysDictOption = new SysDictOption();
        BeanUtils.copyProperties(dictOptionForm, sysDictOption);
        sysDictOptionService.updateById(sysDictOption);
        return R.ok();
    }

    @Operation(summary = "删除字典选项")
    @DeleteMapping("/option/{id}")
    @PreAuthorize("@ss.hasAuthority('sys:dict:del')")
    public R<?> deleteOption(@Parameter(description = "选项ID") @PathVariable Long id) {
        sysDictOptionService.removeById(id);
        return R.ok();
    }
}
