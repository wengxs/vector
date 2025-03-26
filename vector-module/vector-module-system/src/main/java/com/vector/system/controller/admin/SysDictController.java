package com.vector.system.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vector.common.core.pagination.Pageable;
import com.vector.common.core.result.PageResult;
import com.vector.common.core.result.R;
import com.vector.system.dto.SysDictDTO;
import com.vector.system.dto.SysDictOptionDTO;
import com.vector.system.entity.SysDict;
import com.vector.system.entity.SysDictOption;
import com.vector.system.query.SysDictQuery;
import com.vector.system.service.SysDictOptionService;
import com.vector.system.service.SysDictService;
import com.vector.system.vo.SysDictOptionVO;
import com.vector.system.vo.SysDictVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典 Controller
 * @author wengxs
 */
@RestController
@RequestMapping("/sys/dict")
public class SysDictController {

    @Autowired
    private SysDictService sysDictService;
    @Autowired
    private SysDictOptionService sysDictOptionService;

    @GetMapping("/list")
    @PreAuthorize("@ss.hasAuthority('sys:dict:query')")
    public R<PageResult> list(SysDictQuery query) {
        IPage<SysDictVO> page = sysDictService.pageVO(Pageable.getPage(query), query);
        return R.page(page.getRecords(), page.getTotal());
    }

    @GetMapping("/{id}")
    @PreAuthorize("@ss.hasAuthority('sys:dict:query')")
    public R<SysDictVO> get(@PathVariable Long id) {
        return R.ok(sysDictService.getVOById(id));
    }

    @PostMapping
    @PreAuthorize("@ss.hasAuthority('sys:dict:add')")
    public R<?> add(@RequestBody SysDictDTO dictDTO) {
        SysDict sysDict = new SysDict();
        BeanUtils.copyProperties(dictDTO, sysDict);
        sysDictService.save(sysDict);
        return R.ok();
    }

    @PutMapping
    @PreAuthorize("@ss.hasAuthority('sys:dict:edit')")
    public R<?> update(@RequestBody SysDictDTO dictDTO) {
        SysDict sysDict = new SysDict();
        BeanUtils.copyProperties(dictDTO, sysDict);
        sysDictService.updateById(sysDict);
        return R.ok();
    }

    @DeleteMapping("/{ids}")
    @PreAuthorize("@ss.hasAuthority('sys:dict:del')")
    public R<?> delete(@PathVariable List<Long> ids) {
        sysDictService.removeByIds(ids);
        return R.ok();
    }

    @GetMapping("/option/list")
    @PreAuthorize("@ss.hasAuthority('sys:dict:query')")
    public R<List<SysDictOption>> listOption(@RequestParam String dictKey,
                                             @RequestParam(required = false, defaultValue = "false") Boolean onlyEnabled) {
        List<SysDictOption> list = sysDictOptionService.list(new LambdaQueryWrapper<SysDictOption>()
                .eq(SysDictOption::getDictKey, dictKey)
                .eq(onlyEnabled, SysDictOption::getOptionStatus, SysDictOption.OPTION_STATUS_ENABLED)
                .orderByAsc(SysDictOption::getSort));
        return R.ok(list);
    }

    @GetMapping("/option/{id}")
    @PreAuthorize("@ss.hasAuthority('sys:dict:query')")
    public R<SysDictOptionVO> getOption(@PathVariable String id) {
        SysDictOption dictOption = sysDictOptionService.getById(id);
        SysDictOptionVO vo = new SysDictOptionVO();
        BeanUtils.copyProperties(dictOption, vo);
        vo.setId(dictOption.getId());
        return R.ok(vo);
    }

    @PostMapping("/option")
    @PreAuthorize("@ss.hasAuthority('sys:dict:add')")
    public R<?> addOption(@RequestBody SysDictOptionDTO dictOptionDTO) {
        SysDictOption sysDictOption = new SysDictOption();
        BeanUtils.copyProperties(dictOptionDTO, sysDictOption);
        sysDictOptionService.save(sysDictOption);
        return R.ok();
    }

    @PutMapping("/option")
    @PreAuthorize("@ss.hasAuthority('sys:dict:edit')")
    public R<?> updateOption(@RequestBody SysDictOptionDTO dictOptionDTO) {
        SysDictOption sysDictOption = new SysDictOption();
        BeanUtils.copyProperties(dictOptionDTO, sysDictOption);
        sysDictOptionService.updateById(sysDictOption);
        return R.ok();
    }

    @DeleteMapping("/option/{id}")
    @PreAuthorize("@ss.hasAuthority('sys:dict:del')")
    public R<?> deleteOption(@PathVariable Long id) {
        sysDictOptionService.removeById(id);
        return R.ok();
    }
}
