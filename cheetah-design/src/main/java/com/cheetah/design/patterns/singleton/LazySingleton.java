package com.cheetah.design.patterns.singleton;

import java.util.Optional;

public class LazySingleton {

    private int state;

    private static volatile LazySingleton instance=null;    //保证 instance 在所有线程中同步

    private LazySingleton(int state) { //private 避免类在外部被实例化
        this.state = state;
    }

    public static synchronized LazySingleton getInstance(int state) {
        if (null == instance) {
            instance = new LazySingleton(state);
        }
        return instance;
    }

}