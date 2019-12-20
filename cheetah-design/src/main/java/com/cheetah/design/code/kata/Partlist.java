package com.cheetah.design.code.kata;

import static java.lang.String.join;
import static java.util.Arrays.copyOfRange;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Partlist {

    public static String[][] partList(String[] arr) {
        // your code
        String[][] strings = new String[arr.length-1][arr.length-1];
        for (int i = 0; i < arr.length -1; i++) {
            strings[i] = getPart(arr,i);
        }

        System.out.println(Arrays.deepToString(strings));
//        System.out.println(String.format("[%s]",String.join(",",strings[0])));
        return new String[][]{};
    }


    private static String[] getPart(String[] arr,int position) {
        String[] a = new String[arr.length], b = new String[arr.length];
        for (int i = 0; i<arr.length; i++) {
                if(i>position) {
                    b[i]=arr[i];
                } else {
                    a[i]= arr[i];
                }
        }
       a =  getNotNullArray(a);
       b =  getNotNullArray(b);
       return new String[] {String.format("%s, %s",String.join(" ",a),String.join(" ", b))};
    }

    private static String[] getNotNullArray(String[] arr) {
        ArrayList<String> al = new ArrayList<>();
        int size = 0;
        for (String s : arr) {
            if(s != null ) {
                al.add(s);
                ++size;
            }
        }
        String[] strings = new String[size];
        return al.toArray(strings);
    }


    public static String[][] partlistClever(String[] arr) {
        String[][] returnArray = new String[arr.length-1][2];
        for(int i = 0; i < arr.length-1; ++i) {
            returnArray[i][0] = String.join(" ", Arrays.copyOfRange(arr, 0, i+1));
            returnArray[i][1] = String.join(" ", Arrays.copyOfRange(arr, i+1, arr.length));
        }

        return returnArray;
    }


    public static String[][] partlistCleverTwo(String[] arr) {

        // one
        IntStream.range(1, arr.length).mapToObj(i -> new String[] {join(" ", copyOfRange(arr, 0, i)), join(" ", copyOfRange(arr, i, arr.length))}).toArray(String[][]::new);
        // two
        return (String[][]) IntStream.range(1, arr.length)
                .mapToObj(i -> new String[] {
                        Arrays.stream(arr, 0, i).collect(Collectors.joining(" ")),
                        Arrays.stream(arr, i, arr.length).collect(Collectors.joining(" "))}
                )
                .toArray(String[][]::new);

    }

    public static void main(String[] args) {
        String[] s1 = new String[] {"cdIw", "tzIy", "xDu", "rThG"};
        partList(s1);
        s1 = new String[] {"I", "wish", "I", "hadn't", "come"};
        partList(s1);
        String a = "[[cdIw, tzIy xDu rThG], [cdIw tzIy, xDu rThG], [cdIw tzIy xDu, rThG]]";


        String b[][] = new String[2][2];
        b[0] = new String[]{"1", "2", "3"};
        b[1] = new String[]{"4", "5", "6"};

        System.out.println(Arrays.deepToString(b));

//        Arrays.copyOfRange();
    }

}

