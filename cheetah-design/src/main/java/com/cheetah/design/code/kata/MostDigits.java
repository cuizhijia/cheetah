package com.cheetah.design.code.kata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MostDigits {


        public static int findLongest(int[] numbers) {
            if(numbers.length<=0) {
                return 0;
            }
            if(numbers.length==1) {
                return numbers[1];
            }


            return Arrays.asList(numbers).stream()
                         .flatMapToInt(Arrays::stream)
                         .mapToObj(String::valueOf)
                         .max((o1, o2) -> o1.length() > o2.length() ? 1 : o1.length() == o2.length() ? 0 : -1)
                         .map(Integer::parseInt).orElse(0);
        }

    public static void main(String[] args) {
        System.out.println(findLongest(new int[]{-1,1,2,10,500,900}));


    }
}

