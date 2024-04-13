package com.vector.auth.controller;

import com.vector.common.core.result.R;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/hello")
@RestController
public class HelloController {

    @PreAuthorize("hasRole('AD')")
    @GetMapping("/{name}")
    public R<String> say(@PathVariable String name) {
        return R.ok(name);
    }
}
