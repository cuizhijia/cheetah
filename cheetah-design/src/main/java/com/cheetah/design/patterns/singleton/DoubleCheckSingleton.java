package com.cheetah.design.patterns.singleton;

public class DoubleCheckSingleton {


    private volatile static DoubleCheckSingleton instance = null;


    private DoubleCheckSingleton(){}


    public static DoubleCheckSingleton instance() {
        if(null == instance) {
            synchronized (DoubleCheckSingleton.class) {
                if (null == instance) {
                    instance = new DoubleCheckSingleton();
                }
            }

        }
        return instance;
    }
}
