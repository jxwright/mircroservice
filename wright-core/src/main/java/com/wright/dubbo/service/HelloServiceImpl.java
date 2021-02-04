package com.wright.dubbo.service;

import org.apache.dubbo.config.annotation.DubboService;

import com.wright.api.HelloService;

@DubboService
public class HelloServiceImpl  implements HelloService {
    @Override
    public String hello(String name) {
        return "Hello " + name;
    }
}