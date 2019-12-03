package com.cheetah.design.patterns.factory.method;

import com.cheetah.design.patterns.enums.CarEnum;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractCar{

    protected Map<CarEnum,Car> map = new ConcurrentHashMap(16);

    protected void addCar(CarEnum type, Car car) {
        map.put(type,car);
    }



}
