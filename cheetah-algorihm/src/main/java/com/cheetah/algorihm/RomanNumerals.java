package com.cheetah.algorihm;

import java.util.HashMap;
import java.util.Map;

public class RomanNumerals {

    private static final Map<Integer,String> UNIT_NUMBER = new HashMap<>() {{
        put(1,"I");
        put(2,"II");
        put(3,"III");
        put(4,"IV");
        put(5,"V");
        put(6,"VI");
        put(7,"VII");
        put(8,"VIII");
        put(9,"IX");
        put(10,"X");
        put(20,"XX");
        put(30,"XXX");
        put(40,"XL");
        put(50,"L");
        put(60,"LX");
        put(70,"LXX");
        put(80,"LXXX");
        put(90,"XC");
        put(100,"C");
        put(200,"CC");
        put(300,"CCC");
        put(400,"CD");
        put(500,"D");
        put(600,"DC");
        put(700,"DCC");
        put(800,"DCCC");
        put(900,"CM");
        put(1000,"M");
        put(2000,"MM");
        put(3000,"MMM");
    }};


    public static String toRoman(int n) {
        StringBuilder roman = new StringBuilder();
        char[] nChars = String.valueOf(n).toCharArray();
        for (int i = 0; i < nChars.length; i++) {
            char nChar = nChars[i];
            if(nChar == '0') {
                continue;
            }
            roman.append(UNIT_NUMBER.get(Integer.valueOf(nChar + zeros(nChars.length-i-1))));
        }

        return roman.toString();
    }
    private static String zeros(int n) {
        String zero = "";
        for (int i = 0; i < n; i++) {
            zero = zero + "0";
        }
        return zero;
    }




    public static int fromRoman(String romanNumeral) {
        int result = 0;
        romanNumeral = romanNumeral.replace("IV","F")
                                   .replace("IX","G")
                                   .replace("XL","H")
                                   .replace("XC","J")
                                   .replace("CD","K")
                                   .replace("CM","N");

        for (char charStr : romanNumeral.toCharArray()) {

            if (charStr == 'F') {
                result += 4;
            }
            if (charStr == 'G') {
                result += 9;
            }
            if (charStr == 'H') {
                result += 40;
            }
            if (charStr == 'J') {
                result += 90;
            }
            if (charStr == 'K') {
                result += 400;
            }
            if (charStr == 'N') {
                result += 900;
            }
            if (charStr == 'I') {
                result += 1;
            }
            if (charStr == 'V') {
                result += 5;
            }
            if (charStr == 'X') {
                result += 10;
            }
            if (charStr == 'L') {
                result += 50;
            }
            if (charStr == 'C') {
                result += 100;
            }
            if (charStr == 'D') {
                result += 500;
            }
            if (charStr == 'M') {
                result += 1000;
            }
        }
        return result;
    }

    public static void main(String[] args) {

        System.out.println(RomanNumerals.toRoman(1));
        System.out.println(RomanNumerals.toRoman(2));
        System.out.println(RomanNumerals.toRoman(10));
        System.out.println(RomanNumerals.toRoman(1518));


        System.out.println(RomanNumerals.fromRoman("I"));
        System.out.println(RomanNumerals.fromRoman("II"));
        System.out.println(RomanNumerals.fromRoman("IV"));
        System.out.println(RomanNumerals.fromRoman("MDCLXIV"));




    }

}
