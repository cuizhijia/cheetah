package com.cheetah.design.patterns.singleton;

import java.io.ObjectStreamException;

public class InnerSingleton {

    private InnerSingleton() {}

    public static InnerSingleton getInstance() {
        return Inner.instance;
    }
    private static class Inner {
        private static final InnerSingleton instance = new InnerSingleton();

    }

//    private Object readResolve() throws ObjectStreamException {
//        return Inner.instance;
//    }
}
