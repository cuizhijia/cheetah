package com.cheetah.design.patterns.factory.method;

import com.cheetah.design.patterns.enums.CarEnum;

public class Test {

    public static void main(String[] args) {

        AbstractFactory carFactory = new CreateBenz();
        Car car = carFactory.createCar();
        car.run();


        carFactory = new CreateBavarian();
        car = carFactory.createCar();
        car.run();
    }
}
