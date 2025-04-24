package com.cheetah.algorihm;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PickPeaks {


    private static int[][] array = {
            {3,2,3,6,4,1,2,3,2,1,2,2,2,1}};

    private static int[][] array2 = {{1,2,3,6,4,1,2,3,2,1},
            {3,2,3,6,4,1,2,3,2,1,2,3},
            {3,2,3,6,4,1,2,3,2,1,2,2,2,1},
            {2,1,3,1,2,2,2,2,1},
            {2,1,3,1,2,2,2,2}};

    public static void main(String[] args) {
//        for (int[] n : array) {
//            Map<String, List<Integer>> peaks = getPeaks(n);
//            System.out.println(peaks.toString());
//        }
//
        System.out.println(String.format("%0" + "00233".length() + "d", 234));
        System.out.println(String.format("%07d", 234));

        System.out.println(String.format("%0" + "00233".length() + "d", new BigInteger("00233").add(BigInteger.ONE)));

        System.out.println(new BigDecimal("220944795551964828017").add(new BigDecimal("1")).toString());

        System.out.println(incrementString("foobar000"));
        System.out.println(incrementString("foo"));
        System.out.println(incrementString("foobar001"));
        System.out.println(incrementString("foobar99"));
        System.out.println(incrementString("foobar099"));
        System.out.println(incrementString(""));

    }


    private static int isMesa(int[] arr,int before) {
        while (before < arr.length-1) {
            if (arr[before] > arr[before+1]) {
                return before;
            }
            if(arr[before] < arr[before+1]) {
                return -1;
            }
            before++;
        }
        return -1;
    }

    public static Map<String, List<Integer>> getPeaks(int[] arr) {
        // Your code here!
        Map<String,List<Integer>> ans = new HashMap<>() {{
            put("pos",   new ArrayList<>() );
            put("peaks", new ArrayList<>() );
        }};
;
        for (int current = 1; current < arr.length-1; current++) {
            int  before = current-1;
            int  after = current+1;
            if (arr[current] > arr[before]) {
                if(arr[current] > arr[after]) {
                    ans.get("pos").add(current);
                    ans.get("peaks").add(arr[current]);
                }
                if(arr[current] == arr[after]) {
                    int next = isMesa(arr, after);
                    if (next != -1) {
                        ans.get("pos").add(current);
                        ans.get("peaks").add(arr[current]);
                        current = next;
                    }
                }
            }
        }
        return ans;
    }

    public static Map<String,List<Integer>> getPeaksC(int[] arr) {

        Map<String,List<Integer>> ans = new HashMap<>() {{
            put("pos",   new ArrayList<>() );
            put("peaks", new ArrayList<>() );
        }};
        int posMax = 0;
        boolean matchAsc = false;

        for (int i = 1 ; i < arr.length ; i++) {
            if (arr[i-1] < arr[i]) {
                matchAsc = true;
                posMax = i;
            }
            if (matchAsc && arr[i-1] > arr[i]) {
                matchAsc = false;
                ans.get("pos").add(posMax);
                ans.get("peaks").add(arr[posMax]);
            }
        }
        return ans;
    }

    public static Map<String, List<Integer>> getPeaks3(int[] arr) {
        ArrayHelper arrayHelper = new ArrayHelper(arr);

        HashMap<String, List<Integer>> posPeaksMap = new HashMap<>();

        List<Integer> pos = IntStream.range(1, arr.length)
                .filter(arrayHelper::isPeak)
                .boxed().toList();

        List<Integer> peaks = pos.stream()
                .map(i -> arr[i]).toList();

        posPeaksMap.put("pos", pos);
        posPeaksMap.put("peaks", peaks);

        return posPeaksMap;
    }

    private static class ArrayHelper {

        private final int[] array;

        private ArrayHelper(int[] array) {
            this.array = array;
        }

        boolean isPeak(int index) {
            return isBiggerThanDirectPredecessor(index) && isBiggerThanAnySuccessor(index);
        }

        private boolean isBiggerThanDirectPredecessor(int index) {
            return array[index] > array[index - 1];
        }

        private boolean isBiggerThanAnySuccessor(int index) {
            int value = array[index];

            for (int succeedingIndex = index + 1; succeedingIndex < array.length; succeedingIndex++) {
                int succeedingValue = array[succeedingIndex];

                if (value == succeedingValue) {
                    continue;
                }

                return value > succeedingValue;
            }

            return false;
        }

    }




    public static String incrementString(String str) {
        if("".equals(str)) return "1";
        Matcher matcher = Pattern.compile("\\d+$").matcher(str);
        return matcher.find() ?
                matcher.replaceFirst(x -> String.format("%0" + x.group().length() + "d", new BigInteger(x.group()).add(BigInteger.ONE))) :
                str+"1";
    }

}
