package com.wisecoach.controller;

import com.wisecoach.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/2/25 下午3:02
 * {@code @version:} 1.0.0
 */

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/test")
    public String test() {
        testService.test();
        return "ok";
    }
}
