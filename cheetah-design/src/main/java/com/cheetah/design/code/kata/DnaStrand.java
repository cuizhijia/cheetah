package com.cheetah.design.code.kata;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.runners.JUnit4;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DnaStrand {

    public static String makeComplement(String dna) {
        //Your code
         return Stream.of(dna.split("")).map(item -> item.equals("T")?"A"
                 :item.equals("C")?"G"
                 :item.equals("A")?"T"
                 :item.equals("G")?"C":item).collect(Collectors.joining());


    }


    public static String makeComplementlever(String dna) {
        dna = dna.replaceAll("A","Z");
        dna = dna.replaceAll("T","A");
        dna = dna.replaceAll("Z","T");
        dna = dna.replaceAll("C","Z");
        dna = dna.replaceAll("G","C");
        dna = dna.replaceAll("Z","G");
        return dna;
    }

    public static void main(String[] args) {
        char t = 'T';

        System.out.println(makeComplement("TAACG"));
    }


        @Test
        public void test01() {
            assertEquals("TTTT", DnaStrand.makeComplement("AAAA"));
        }
        @Test
        public void test02() {
            assertEquals("TAACG", DnaStrand.makeComplement("ATTGC"));
        }
        @Test
        public void test03() {
            assertEquals("CATA", DnaStrand.makeComplement("GTAT"));
        }
    }
