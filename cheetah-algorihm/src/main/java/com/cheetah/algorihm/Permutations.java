package com.cheetah.algorihm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Permutations {

    public static void main(String[] args) {
        System.out.println(singlePermutations("abcd"));

//        System.out.println(singlePermutations("a"));
//        System.out.println(singlePermutations("ab"));
//        System.out.println(singlePermutations("aabb"));
//        System.out.println(singlePermutations("abcdefghijklmnopqrstuvwxyz"));
//        System.out.println(singlePermutations("aabbccddeeffgghhtt"));
//        System.out.println(singlePermutations("skdfjksdjfskdhfkj"));

    }
    public static void backtrack(int n, List<String> output, Set<String> res, int first) {
        if (first == n) {
            res.add(String.join("", output));
        }
        for (int i = first; i < n; i++) {
            Collections.swap(output, first, i);
            backtrack(n, output, res, first + 1);
            Collections.swap(output, first, i);
        }
    }


//    public List<List<Integer>> permute(int[] nums) {
//
//        List<List<Integer>> list = new ArrayList<>();
//        if (nums.length == 1) {
//            list.add(Arrays.stream(nums).boxed().toList());
//        } else {
//            for (int i = 0; i < nums.length; i++) {
//                List<List<Integer>> temp = permute(s.substring(0, i) + s.substring(i + 1));
//                for (List<Integer> string : temp) {
//                    list.add(s.charAt(i) + string);
//                }
//            }
//        }
//        return new ArrayList<>(set);
//    }


    public static List<String> singlePermutations1(String s) {
        Set<String> set = new HashSet<>();
        if (s.length() == 1) {
            set.add(s);
        } else {
            for (int i = 0; i < s.length(); i++) {
                List<String> temp = singlePermutations(s.substring(0, i) + s.substring(i + 1));
                for (String string : temp) {
                    set.add(s.charAt(i) + string);
                }
            }
        }

        return new ArrayList<>(set);
    }







    public static List<String> singlePermutations(String input) {
        Set<String> res = new HashSet<>();
        backtrack(input.length(), Arrays.asList(input.split("")), res, 0);
        return new ArrayList<>(res);

    }

    private static String swap(String s,int index1,int index2) {
        StringBuilder sb = new StringBuilder(s);
        char temp = sb.charAt(index1);
        sb.setCharAt(index1, sb.charAt(index2));
        sb.setCharAt(index2, temp);
        return sb.toString();
    }
}
