package com.example.demo.controller;

import com.example.demo.pojo.User;
import com.example.demo.pojo.UserResource;
import com.example.demo.service.AuthService;
import com.example.demo.service.ResourceService;
import com.example.demo.util.MockUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    AuthService authService;


    @PostMapping(" /admin/addUser")
    public String addUser(@RequestBody User user, @RequestBody UserResource resource) {
        List<String> errorList = MockUtil.validateUser(user);
        if(!errorList.isEmpty()) {
            return "error:" + String.join(";", errorList);
        }
        boolean access = authService.checkAdmin(user);
        if(!access) {
            return "only admin could add resource";
        }
        authService.addResource(resource);
        return "resource is granted successfully!";
    }

}
