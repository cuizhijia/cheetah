package com.cheetah.design.patterns.prototype;

public class Circle implements Shape{

    @Override
    public String name() {
        return "Circle";
    }

    @Override
    public Double countArea(double l) {
        return l*l;
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
