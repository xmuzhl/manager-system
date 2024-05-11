package com.example.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.pojo.DemoException;
import com.example.demo.pojo.User;
import com.example.demo.pojo.UserResource;
import com.example.demo.service.AuthService;
import com.example.demo.util.DemoConstants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    private HashMap<Integer, List<String>> cacheMap;

    private ArrayList<UserResource> resList;

    private Gson gson;

    private AuthServiceImpl() {
        gson = new Gson();
//        String path = getClass().getResource("/").getPath();
//        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String path = "file:/D:/";
        try {
            File file = ResourceUtils.getFile(path + "test.json");
            String json = FileUtils.readFileToString(file, "UTF-8");
            resList = gson.fromJson(json, new TypeToken<List<UserResource>>() {
            }.getType());
            cacheMap = new HashMap<>();
            for (UserResource re : resList) {
                cacheMap.put(re.getUserId(),re.getResList());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addResource(UserResource resource) {
        Integer userId = resource.getUserId();
        if(!cacheMap.containsKey(userId)) {
            throw new DemoException(DemoConstants.USER_NOT_FOUND + " userId: " + userId);
        }
        cacheMap.put(userId,resource.getResList());
        refresh(resource);
        return true;
    }

    @Override
    public boolean checkAdmin(User user) {
        // todo
        if(user != null && user.getRole().equals("admin") ) return true;
        return false;
    }

    @Override
    public List<String> getUserResource(Integer userId) {
        return cacheMap.get(userId);
    }

    synchronized private void refresh(UserResource resource) {
        for (UserResource ur : resList) {
            if(ur.getUserId().equals(resource.getUserId())) {
                ur.setResList(resource.getResList());
                break;
            }
        }
        FileWriter file = null;
        try {
//            String s = getClass().getResource("/").getPath();
            String s = "D:/";
            file = new FileWriter(s + "test.json");
            file.write(gson.toJson(resList));
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
