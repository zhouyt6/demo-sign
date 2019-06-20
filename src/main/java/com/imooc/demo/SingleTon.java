package com.imooc.demo;

public class SingleTon {
    //创建实例对象
    private static SingleTon ourInstance = new SingleTon();
    //提供返回实例的方法
    public static SingleTon getInstance() {
        return ourInstance;
    }
    /*构造函数初始化*/
    private SingleTon() {
    }
}
