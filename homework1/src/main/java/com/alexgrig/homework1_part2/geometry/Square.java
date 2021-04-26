package com.alexgrig.homework1_part2.geometry;

public class Square implements GeometryShape {

    private int x;
    private int y;
    private double side;

    public Square(int x, int y, double side) {
        this.x = x;
        this.y = y;
        this.side = side;
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
        return side * side;
    }

    @Override
    public double getPerimeter() {
        return 4 * side;
    }
}
