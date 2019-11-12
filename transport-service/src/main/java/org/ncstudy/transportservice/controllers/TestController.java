package org.ncstudy.transportservice.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class TestController {
    @GetMapping("/adm")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public String testAdmin(@RequestParam String mess){
        return mess;
    }

    @GetMapping("/usr")
    public String testUser(@RequestParam String mess){
        return mess;
    }
}
