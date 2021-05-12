package com.nagaro.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statement")
public class StatementController {
    @GetMapping
    public String serviceName() {
        return "statement service";
    }
}
