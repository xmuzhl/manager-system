package com.example.demo.controller;

import com.example.demo.pojo.User;
import com.example.demo.service.ResourceService;
import com.example.demo.util.MockUtil;
import org.apache.catalina.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private ResourceService resourceService;

    @PostMapping("/users/{resource}")
    public String getResource(@RequestBody User user, @PathVariable String resource){
        List<String> errorList = MockUtil.validateUser(user);
        if(!errorList.isEmpty()) {
            return "error:" + String.join(";", errorList);
        }
        boolean access = resourceService.checkAccess(user, resource);
        if(access) {
            return resource + " success";
        } else {
            return "403";
        }
    }
}
