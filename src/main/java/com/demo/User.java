package com.demo;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable{

    private  Integer id;
    private  Integer age;
    private  String username;
    private  String jsonStr;

}
