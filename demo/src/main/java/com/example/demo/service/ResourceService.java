package com.example.demo.service;

import com.example.demo.pojo.User;

import java.util.List;

public interface ResourceService {

    boolean checkAccess(User user, String resource);


}
