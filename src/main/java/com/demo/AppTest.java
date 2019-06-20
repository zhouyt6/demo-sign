package com.demo;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppTest {

    public static void main(String[] args) {

    }
    /**
     * 利用正则表达式判断字符串是否是数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

    @Test
    public  void test() {
        String str = "23232323424343434";
        boolean numeric = isNumeric(str);
        Long aLong = Long.parseLong(str);
        if (aLong.getClass().equals(Long.class)) {
            System.out.println(true);
        }
        System.out.println(numeric);
    }
}
