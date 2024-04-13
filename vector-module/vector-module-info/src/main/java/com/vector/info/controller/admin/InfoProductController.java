package com.vector.info.controller.admin;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vector.common.core.pagination.Pageable;
import com.vector.common.core.result.PageResult;
import com.vector.common.core.result.R;
import com.vector.info.entity.InfoProduct;
import com.vector.info.service.InfoProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/info/product")
public class InfoProductController {

    @Autowired
    private InfoProductService infoProductService;

    @PreAuthorize("hasRole('test')")
    @GetMapping("/list")
    public R<PageResult> list(@RequestParam Map<String, Object> params) {
        System.out.println("权限");
        for (GrantedAuthority authority : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
            System.out.println(authority.getAuthority());
        }
        InfoProduct query = Pageable.getQuery(params, InfoProduct.class);
        IPage<InfoProduct> page = infoProductService.page(Pageable.getPage(params), new LambdaQueryWrapper<>(InfoProduct.class)
                .like(StringUtils.isNotBlank(query.getName()), InfoProduct::getName, query.getName())
                .like(StringUtils.isNotBlank(query.getFactory()), InfoProduct::getFactory, query.getFactory())
        );
        return R.page(page.getRecords(), page.getTotal());
    }

    @PreAuthorize("hasAuthority('sys:user:edit')")
    @GetMapping("/{id}")
    @SentinelResource("productGet")
    public R<InfoProduct> get(@PathVariable Long id) {
        return R.ok(infoProductService.getById(id));
    }

    @PostMapping
    public R<?> add(@RequestBody InfoProduct infoProduct) {
        infoProductService.save(infoProduct);
        return R.ok();
    }

    @PutMapping
    public R<?> update(@RequestBody InfoProduct infoProduct) {
        infoProductService.updateById(infoProduct);
        return R.ok();
    }

    @DeleteMapping("/{ids}")
    public R<?> delete(@PathVariable List<Long> ids) {
        infoProductService.removeByIds(ids);
        return R.ok();
    }
}
