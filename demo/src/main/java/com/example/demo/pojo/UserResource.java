package com.example.demo.pojo;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserResource {
    private Integer userId;

    private List<String> resList;
}
