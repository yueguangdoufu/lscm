package com.sanzu.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public Map<String ,String> check3(Exception e){
        e.printStackTrace();
        System.out.println("后台服务出错，调用check方法~~~~");
        Map<String ,String> map = new HashMap<>();
        map.put("Status","error");
        map.put("Text","未知错误");
        return map;
    }
}
