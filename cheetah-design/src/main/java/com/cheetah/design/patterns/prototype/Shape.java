package com.cheetah.design.patterns.prototype;

public interface Shape extends Cloneable{

    public String name();

    public Double countArea(double r);

    public Object clone();
}
