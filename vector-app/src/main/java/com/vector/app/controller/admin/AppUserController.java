package com.vector.app.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vector.common.core.pagination.Pageable;
import com.vector.common.core.result.PageResult;
import com.vector.common.core.result.R;
import com.vector.app.pojo.dto.AppUserDTO;
import com.vector.app.pojo.entity.AppUser;
import com.vector.app.pojo.query.AppUserQuery;
import com.vector.app.pojo.vo.AppUserVO;
import com.vector.app.service.AppUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户信息 Controller
 * @author wengxs
 * @date 2025/03/31
 */
@RestController
@RequestMapping("/app/user")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @GetMapping("/list")
    @PreAuthorize("@ss.hasAuthority('app:user:query')")
    public R<PageResult> list(AppUserQuery query) {
        IPage<AppUserVO> page = appUserService.pageVO(Pageable.getPage(query), query);
        return R.page(page.getRecords(), page.getTotal());
    }

    @GetMapping("/{id}")
    @PreAuthorize("@ss.hasAuthority('app:user:query')")
    public R<AppUserVO> get(@PathVariable Long id) {
        return R.ok(appUserService.getVOById(id));
    }

    @PostMapping
    @PreAuthorize("@ss.hasAuthority('app:user:add')")
    public R<?> add(@RequestBody AppUserDTO userDTO) {
        AppUser appUser = new AppUser();
        BeanUtils.copyProperties(userDTO, appUser);
        appUserService.save(appUser);
        return R.ok();
    }

    @PutMapping
    @PreAuthorize("@ss.hasAuthority('app:user:edit')")
    public R<?> update(@RequestBody AppUserDTO userDTO) {
        AppUser appUser = new AppUser();
        BeanUtils.copyProperties(userDTO, appUser);
        appUserService.updateById(appUser);
        return R.ok();
    }

    @DeleteMapping("/{ids}")
    @PreAuthorize("@ss.hasAuthority('app:user:del')")
    public R<?> delete(@PathVariable List<Long> ids) {
        appUserService.removeByIds(ids);
        return R.ok();
    }
}
