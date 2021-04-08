package com.alexgrig.homework1_part2.geometry;

public class Triangle implements GeometryShape {

    private int x;
    private int y;
    private double side1;
    private double side2;
    private double side3;

    public Triangle(int x, int y, double side1, double side2, double side3) {
        this.x = x;
        this.y = y;
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public double getArea() {
        double halfPer = getPerimeter() / 2;
        return Math.sqrt(halfPer * (halfPer - side1) * (halfPer - side2) * (halfPer - side3));
    }

    @Override
    public double getPerimeter() {
        return side1 + side2 + side3;
    }
}
