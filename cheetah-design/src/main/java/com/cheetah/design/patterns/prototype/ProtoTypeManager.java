package com.cheetah.design.patterns.prototype;

import java.util.HashMap;
import java.util.Map;

public class ProtoTypeManager {

    private Map<String,Shape> shapeMap = new HashMap<>();

    public ProtoTypeManager() {
        shapeMap.put("Circle",new Circle());
        shapeMap.put("Square",new Square());
    }
    public void addShape(String key,Shape obj){
        shapeMap.put(key,obj);
    }
    public Shape getShape(String key) {
        return (Shape) shapeMap.get(key).clone();
    }

}
