package com.example.demo.service.impl;

import com.example.demo.pojo.DemoException;
import com.example.demo.pojo.User;
import com.example.demo.service.AuthService;
import com.example.demo.service.ResourceService;
import com.example.demo.util.DemoConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private AuthService authService;


    @Override
    public boolean checkAccess(User user, String resource) {
        List<String> userResource = authService.getUserResource(user.getUserId());
        if(userResource == null) {
            throw new DemoException(DemoConstants.USER_NOT_FOUND + " userId: " + user.getUserId());
        }
        for (String s : userResource) {
            if(s.equals(resource)) {
                return true;
            }
        }
        return false;
    }
}
