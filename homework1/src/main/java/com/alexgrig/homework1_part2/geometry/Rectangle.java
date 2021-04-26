package com.alexgrig.homework1_part2.geometry;

public class Rectangle implements GeometryShape{
    private int x;
    private int y;
    private double height;
    private double width;

    public Rectangle(int x, int y, double height, double width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
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
        return height * width;
    }

    @Override
    public double getPerimeter() {
        return (height + width) * 2;
    }
}
