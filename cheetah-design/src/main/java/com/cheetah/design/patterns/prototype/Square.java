package com.cheetah.design.patterns.prototype;

public class Square implements Shape{

    @Override
    public String name() {
        return "Square";
    }

    @Override
    public Double countArea(double r) {
        return 3.14*r*r;
    }

    @Override
    public Object clone() {
        try{
            return super.clone();
        } catch (CloneNotSupportedException e) {e.printStackTrace();}
        return null;
    }
}
