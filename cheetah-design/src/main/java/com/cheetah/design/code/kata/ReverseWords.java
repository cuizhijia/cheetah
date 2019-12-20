package com.cheetah.design.code.kata;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class ReverseWords {

    public static String reverseWords(final String original) {
        return Arrays.stream(original.split("(?<=\\s)|(?=\\s+)")).map(ReverseWords::reverseOneWordsByBuilder).collect(Collectors.joining());
    }

    private static String reverseOneWordsByBuilder(String words) {
        return new StringBuffer(words).reverse().toString();
    }

    private static String reverseOneWords(String words) {
        char[] chars = words.toCharArray();
        char[] reverseChars = new char[chars.length];
        int z = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            reverseChars[z++] = chars[i];
        }
        return String.valueOf(reverseChars);

    }

    public static String reverseWordsClever(final String original)
    {
        return Arrays.stream(original.split("(?<=\\s)|(?=\\s+)"))
                .map(str -> new StringBuilder(str).reverse().toString())
                .collect(Collectors.joining());
    }

    private static List<Character> toCharacterList(char[] chars) {
        List<Character> list = new ArrayList<>(chars.length);
        for (char aChar : chars) {
            list.add(aChar);
        }
        return list;
    }


    @Test
    public void exampleCases() {
        assertEquals("ehT kciuq nworb xof spmuj revo eht yzal .god", ReverseWords.reverseWords("The quick brown fox jumps over the lazy dog."));
        assertEquals("elppa", ReverseWords.reverseWords("apple"));
        assertEquals("a b c d", ReverseWords.reverseWords("a b c d"));
        assertEquals("elbuod  decaps  sdrow", ReverseWords.reverseWordsClever("double  spaced  words"));
    }
}
