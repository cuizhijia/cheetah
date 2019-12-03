package com.cheetah.design.patterns.factory.method;

import com.cheetah.design.patterns.enums.CarEnum;

public class BavarianCar extends AbstractCar implements Car {

    @Override
    public void run() {
        System.out.println("BMW is running");
    }


}
