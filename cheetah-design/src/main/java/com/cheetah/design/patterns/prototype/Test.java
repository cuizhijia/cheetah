package com.cheetah.design.patterns.prototype;

public class Test {


    public static void main(String[] args) {

        ProtoTypeManager protoTypeManager = new ProtoTypeManager();

        Shape circle = protoTypeManager.getShape("Circle");
        Shape circle1 = protoTypeManager.getShape("Circle");
        System.out.println(circle.equals(circle1));
        System.out.println(circle.countArea(8.00));
        System.out.println(circle.countArea(4.00));

        Shape square = protoTypeManager.getShape("Square");
        Shape square1 = protoTypeManager.getShape("Square");
        System.out.println(square.equals(square1));
        System.out.println(square.countArea(8.00));
        System.out.println(square1.countArea(4.00));

    }
}
