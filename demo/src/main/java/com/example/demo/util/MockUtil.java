package com.example.demo.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.pojo.User;
import com.example.demo.pojo.UserResource;
import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MockUtil {

    public static List<String> validateUser(User user) {
        ArrayList<String> list = new ArrayList<>();
        if(user == null) {
            list.add("user data should be contained");
            return list;
        }
        if(user.getUserId() == null) {
            list.add("userId is null");
        }
        if(user.getAccountName() == null) {
            list.add("accountName is null");
        }
        if(user.getRole() == null || (!user.getRole().equals("user") && !user.getRole().equals("admin"))) {
            list.add("role is wrong");
        }
        return list;
    }




}
