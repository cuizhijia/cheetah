package com.cheetah.design.patterns.factory.method;

import com.cheetah.design.patterns.enums.CarEnum;

public class BenzCar extends AbstractCar implements Car{
    @Override
    public void run() {
        System.out.println("Benz is running");
    }


}
