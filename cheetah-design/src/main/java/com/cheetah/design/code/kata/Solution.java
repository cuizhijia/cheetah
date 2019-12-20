package com.cheetah.design.code.kata;


import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertArrayEquals;
public class Solution {

    public static String[] capitalize(String s){
        char[] chars = s.toUpperCase().toCharArray();
        char[] firstLowerStr = new char[chars.length] ,
                firstUpperStr= new char[chars.length];
        for (int i = 0;i<=chars.length - 1; i++) {
            if ((i & 1) != 1) {
                firstLowerStr[i] = Character.toLowerCase(chars[i]);
                firstUpperStr[i] =  chars[i];
            } else {
                firstLowerStr[i] = chars[i];
                firstUpperStr[i] = Character.toLowerCase(chars[i]);
            }
        }
        return new String[]{String.valueOf(firstUpperStr),String.valueOf(firstLowerStr)};
    }

    public static String[] capitalizeClever(String s){
        String[] res = new String[]{"" , ""};
        for (int i = 0; i < s.length(); i++) {
            res[i & 1] += s.substring(i, i + 1).toUpperCase();
            res[i & 1 ^ 1] += s.substring(i, i + 1);
        }
        return res;
    }

    public static String[] capitalizeClever1(String s){
        StringBuilder sb1 = new StringBuilder(s),
                sb2 = new StringBuilder(s.toUpperCase());
        IntStream.range(0, s.length())
                .filter(  i -> (i&1)==0 )
                .forEach( i -> { sb1.setCharAt(i, sb2.charAt(i));
                    sb2.setCharAt(i, s.charAt(i)); });
        return new String[] {sb1.toString(), sb2.toString()};
    }

    @Test
    public void basicTests(){
        assertArrayEquals(new String[]{"AbCdEf", "aBcDeF"}, Solution.capitalize("abcdef"));
        assertArrayEquals(new String[]{"CoDeWaRs", "cOdEwArS"}, Solution.capitalize("codewars"));
        assertArrayEquals(new String[]{"AbRaCaDaBrA", "aBrAcAdAbRa"}, Solution.capitalize("abracadabra"));
        assertArrayEquals(new String[]{"CoDeWaRrIoRs", "cOdEwArRiOrS"}, Solution.capitalize("codewarriors"));
    }
}
