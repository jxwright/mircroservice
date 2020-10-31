package com.wright.dubbo.service;

import org.apache.dubbo.config.annotation.Service;

import com.wright.api.HelloService;

@Service
public class HelloServiceImpl  implements HelloService {
    @Override
    public String hello(String name) {
        return "Hello " + name;
    }
}