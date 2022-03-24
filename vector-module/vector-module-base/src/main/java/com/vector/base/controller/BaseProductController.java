package com.vector.base.controller;

import com.vector.core.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zorg
 * @date 2022/3/24
 */
@RestController
@RequestMapping("/base/product")
public class BaseProductController {

    @Value("${test-data}")
    private String testData;

    @GetMapping("/test")
    public R test() {
        return R.ok(testData);
    }
}
