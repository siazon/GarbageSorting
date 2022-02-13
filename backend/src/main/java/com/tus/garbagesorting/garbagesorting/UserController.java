package com.tus.garbagesorting.garbagesorting;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @RequestMapping("/hello")
    public String index(){
        return "Hello Spring Boot";
    }
}
