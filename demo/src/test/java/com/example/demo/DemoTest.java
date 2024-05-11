package com.example.demo;


import com.example.demo.controller.AdminController;
import com.example.demo.controller.UserController;
import com.example.demo.pojo.User;
import com.example.demo.pojo.UserResource;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;


@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoTest {
    @Autowired
    UserController userController;

    @Autowired
    AdminController adminController;

    @Test
    void tesUserRole() {
        User user = new User();
        user.setUserId(123456);
        user.setRole("user");
        user.setAccountName("XXX");

        System.out.println("====Test for common resource API===");
        String resource_a = userController.getResource(user, "Resource A");
        System.out.println(resource_a);

        System.out.println("====Test for unpermitted resource API===");
        String resource_X = userController.getResource(user, "Resource X");
        System.out.println(resource_X);

        System.out.println("===add resource X for user 123456 by himself===");
        UserResource userResource = new UserResource();
        userResource.setUserId(user.getUserId());
        userResource.setResList(Arrays.asList("Resource A", "Resource B", "Resource X"));
        String addUserResult = adminController.addUser(user, userResource);
        System.out.println(addUserResult);

        System.out.println("===add resource X for user 123456 by admin===");
        User admin = new User();
        admin.setUserId(1);
        admin.setRole("admin");
        admin.setAccountName("XX");
        String addUserByAdmin = adminController.addUser(admin, userResource);
        System.out.println(addUserByAdmin);

        System.out.println("====Test for new added resource API===");
        String resource_X2 = userController.getResource(user, "Resource X");
        System.out.println(resource_X2);


        System.out.println("===Test for wrong user data===");
        User blankUser = new User();
        String errorMsg = userController.getResource(user, "1");
        System.out.println(errorMsg);
    }

}
