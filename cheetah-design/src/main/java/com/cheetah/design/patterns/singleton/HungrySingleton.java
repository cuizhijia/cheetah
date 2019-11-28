package com.cheetah.design.patterns.singleton;

public class HungrySingleton {

    private static final HungrySingleton instance=new HungrySingleton();

    private HungrySingleton(){}
    public static HungrySingleton getInstance() {
        return instance;
    }
}