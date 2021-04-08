package com.alexgrig.homework1_part2.geometry;

//Задача. Реализовать иерархию объектов Circle, Rect, Triangle, Square
public interface GeometryShape {
    int getX();
    int getY();
    double getArea();
    double getPerimeter();
}
