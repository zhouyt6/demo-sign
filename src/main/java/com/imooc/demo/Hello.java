package com.imooc.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class Hello {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String test(@RequestParam(name = "type", required = false) String type) {
        System.out.println("这个hello Test程序Spring boot" + type);
        if (Objects.equals(type, "add")) {
            System.out.println("true");
        }
        return "这个hello Test程序Spring boot"+type;
    }
}
