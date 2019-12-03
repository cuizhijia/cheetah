package com.cheetah.design.patterns.factory.method;

import com.cheetah.design.patterns.enums.CarEnum;

public class CreateBavarian implements AbstractFactory{
    @Override
    public Car createCar(){
        return new BavarianCar();
    }
}
