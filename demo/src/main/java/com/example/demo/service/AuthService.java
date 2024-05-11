package com.example.demo.service;

import com.example.demo.pojo.User;
import com.example.demo.pojo.UserResource;

import java.util.List;

public interface AuthService {

    boolean addResource(UserResource resource);

    boolean checkAdmin(User user);

    List<String> getUserResource(Integer userId);

}
