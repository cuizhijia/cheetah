package com.cheetah.design.code.kata;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class AlphabetWar {

    private static final Map<String,Integer> POWER_MAP = new HashMap<>();

    static {
        POWER_MAP.put("w",4);
        POWER_MAP.put("p",3);
        POWER_MAP.put("b",2);
        POWER_MAP.put("s",1);
        POWER_MAP.put("m",-4);
        POWER_MAP.put("q",-3);
        POWER_MAP.put("d",-2);
        POWER_MAP.put("z",-1);
    }

    private static final String[] FIGHT_RESULT = new String[] {
            "Left",
            "Right",
            " side wins!",
            "Let's fight again!"
    };



    public static String alphabetWar(String fight){
        Integer power = Arrays.stream(fight.split(""))
                .map(p -> POWER_MAP.get(p))
                .filter(Objects::nonNull)
                .reduce(Math::addExact).orElse(0);
        return power == 0 ? FIGHT_RESULT[3] : (power > 0 ? FIGHT_RESULT[0] : FIGHT_RESULT[1]) + FIGHT_RESULT[2];
    }


    public static String alphabetWarClever(String fight){
        String lpower = "wpbs_zdqm";

        int result = fight.chars()
                .reduce(0, (a, b) -> a + (lpower.indexOf(b)==-1 ? 0 : lpower.indexOf(b)-4));

        return result == 0 ? "Let's fight again!" : (result < 0 ? "Left" : "Right") + " side wins!";
    }

        public static void main(String[] args) {
        System.out.println(alphabetWar("z"));
        System.out.println(alphabetWar("zdqmwpbs"));
        System.out.println(alphabetWar("zzzzs"));
        System.out.println(alphabetWar("wwwwwwz"));
    }



}
