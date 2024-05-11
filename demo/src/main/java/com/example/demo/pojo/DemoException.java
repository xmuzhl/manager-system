package com.example.demo.pojo;


import lombok.Data;
import org.springframework.http.HttpEntity;

@Data
public class DemoException extends RuntimeException  {

    private String errorInfo;

    public DemoException(String errorInfo) {
        super(errorInfo);
    }

}