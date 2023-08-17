package com.fjc.demo.example;

/**
 * author 凡
 *
 */
public class DemoTestInstance {
    public static String toStr(Object value, String defaultValue){
        if(null == value){
            return defaultValue;
        }
        if(value instanceof String){
            return (String)value;
        }
        return value.toString();
    }
    public static void main(String[] args) {
        Object value = 100;
        System.out.println(toStr(value,"10000"));
        System.out.println(10);
    }
}
