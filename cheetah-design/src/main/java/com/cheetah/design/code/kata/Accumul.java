package com.cheetah.design.code.kata;

import java.util.*;
import java.util.stream.Stream;

import static sun.jvm.hotspot.runtime.BasicObjectLock.size;

public class Accumul {

    public static String accum(String s) {

        Map<Integer,String> map = new TreeMap<>();

        String[] split = s.split("");
        for (int i = split.length - 1; i >= 0; i--) {
            map.put(i+1,split[i]);
        }

        String result = "";
        for (Map.Entry<Integer,String> stringIntegerEntry : map.entrySet()) {
            String accum = getAccum(stringIntegerEntry.getValue(),stringIntegerEntry.getKey());
            result+=accum+"-";
        }
        System.out.println(result.substring(0,result.lastIndexOf("-")));

        // your code
        return result;
    }

    private static String getAccum(String str,Integer length) {

        String result = "";
        for (int i = 0; i < length; i++) {
            result += str;
        }
        if(length == 1) {
            return str.toUpperCase();
        }
        result =  result.toLowerCase();
        result = result.substring(0,1).toUpperCase().concat(result.substring(1));
        System.out.println(result);
        return result;
    }


    public static String accumLever(String s) {
        String[] strings = s.toLowerCase().split("");
        for (int i = 0; i<strings.length; i++){
            strings[i] = getAccumClever(strings[i],i+1);
        }
        return String.join("-", strings);
    }

    private static String getAccumClever(String str,Integer length) {

        String result = "";
        for (int i = 0; i < length; i++) {
            result += str;
        }
        if(length == 1) {
            return result.toUpperCase();
        }
        return result.substring(0,1).toUpperCase() + result.substring(1);
    }

    public static void main(String[] args) {
        accumLever("ZpglnRxqenU");
    }

}
