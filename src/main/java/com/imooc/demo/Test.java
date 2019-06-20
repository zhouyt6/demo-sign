package com.imooc.demo;

public class Test {

        public static void main(String[] args) {
            SingleTon2 singleTon = SingleTon2.getInstance();
            SingleTon2 singleTon1 = SingleTon2.getInstance();
            System.out.println(singleTon==singleTon1);
        }

}
