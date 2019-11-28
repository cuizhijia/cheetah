package com.cheetah.design.patterns.singleton;

import org.springframework.util.Assert;

public class Test {

    public static void main(String[] args) {

        // Lazy
        LazySingleton instanceLazy = LazySingleton.getInstance(1);
        LazySingleton instanceLazy1 = LazySingleton.getInstance(2);
        Assert.isTrue(instanceLazy.equals(instanceLazy1),"instance is not noe");

        // Hungry
        HungrySingleton instanceHungry = HungrySingleton.getInstance();
        HungrySingleton instanceHungry1 = HungrySingleton.getInstance();
        Assert.isTrue(instanceHungry.equals(instanceHungry1),"instance is not noe");



    }
}
