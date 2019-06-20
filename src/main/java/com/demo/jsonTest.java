package com.demo;

import com.alibaba.fastjson.JSON;

import java.util.LinkedHashMap;
import java.util.Map;

public class jsonTest {

    public static void main(String[] args) {
        String str = "{\"id\": \"2133\",\"name\": \"testName\",\"age\": \"123age\"}";
        Map mapType = JSON.parseObject(str,LinkedHashMap.class);
        int i = mapType.size();
        for (Object o : mapType.keySet()) {
            System.out.println(o+":" +mapType.get(o)+": "+i);
        }
//        String str = "{\"id\":\"zhangsan\",\"name\":\"lisi\",\"age\":\"wangwu\",\"3\":\"maliu\"}";
        //第一种方式
//        Map maps = (Map) JSON.parse(str);
//        System.out.println("这个是用JSON类来解析JSON字符串");
//        for (Object map : maps.entrySet()){
//            System.out.println(((Map.Entry)map).getKey()+"     " + ((Map.Entry)map).getValue());
//        }
//        //第二种方式
//        Map mapTypes = JSON.parseObject(str);
//        System.out.println("这个是用JSON类的parseObject来解析JSON字符串!!!");
//        for (Object obj : mapTypes.keySet()){
//            System.out.println("key为："+obj+"值为："+mapTypes.get(obj));
//        }
        //第三种方式
//        Map mapType = JSON.parseObject(str,LinkedHashMap.class);
//        System.out.println("这个是用JSON类,指定解析类型，来解析JSON字符串!!!");
//        for (Object obj : mapType.keySet()){
//            System.out.println("key为："+obj+"值为："+mapType.get(obj));
//        }
//        //第四种方式
//        /**
//         * JSONObject是Map接口的一个实现类
//         */
//        Map json = (Map) JSONObject.parse(str);
//        System.out.println("这个是用JSONObject类的parse方法来解析JSON字符串!!!");
//        for (Object map : json.entrySet()){
//            System.out.println(((Map.Entry)map).getKey()+"  "+((Map.Entry)map).getValue());
//        }

    }
}
