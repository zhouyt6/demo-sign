package com.imooc.demo;

public class SingleTon2 {
    //1,构造函数私有化
    private SingleTon2(){};
        //2.创建实例 ,懒汉模式
    private static SingleTon2 newInstance;

    public static  synchronized SingleTon2 getInstance() {
        if (newInstance == null) {
            System.out.println("SinleTon2创建实例");
          return newInstance = new SingleTon2();
        }else {
            System.out.println("SinleTon2未创建实例");
            return newInstance;
        }
    }

}
