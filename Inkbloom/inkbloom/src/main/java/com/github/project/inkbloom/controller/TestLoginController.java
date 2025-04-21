package com.github.project.inkbloom.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestLoginController {
    @RequestMapping("/test")
    public String test(){
        return "Test my TestController";
    }
}
