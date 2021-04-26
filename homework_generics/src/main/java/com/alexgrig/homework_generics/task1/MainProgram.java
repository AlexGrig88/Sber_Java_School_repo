package com.alexgrig.homework_generics.task1;

public class MainProgram {
    public static void main(String[] args) {
        CountMap<Integer> map1 = new CountMapImpl<>();
        map1.add(10);
        map1.add(10);
        map1.add(5);
        map1.add(6);
        map1.add(5);
        map1.add(10);


        System.out.println("5: " + map1.getCount(5));
        System.out.println("6: " + map1.getCount(6));
        System.out.println("10: " + map1.getCount(10));
        System.out.println("size = " + map1.size());

        System.out.println("========================================");
        CountMap<Integer> map2 = new CountMapImpl<>();
        map2.add(10);
        map2.add(10);
        map2.add(10);
        map2.add(10);
        map2.add(4);
        map2.add(1);
        map2.add(4);
        map2.add(5);
        map2.add(5);
        map2.add(5);

        System.out.println("10: " + map2.getCount(10));
        System.out.println("4: " + map2.getCount(4));
        System.out.println("1: " + map2.getCount(1));
        System.out.println("5: " + map2.getCount(5));
        System.out.println("size = " + map2.size());

        System.out.println("========================================");
        map1.addAll(map2);
        System.out.println("5: " + map1.getCount(5));
        System.out.println("6: " + map1.getCount(6));
        System.out.println("10: " + map1.getCount(10));
        System.out.println("4: " + map1.getCount(4));
        System.out.println("1: " + map1.getCount(1));

        System.out.println("size = " + map1.size());
        System.out.println("========================================");
        System.out.println("Удалим 4. Их было в нашем контейнере - " + map1.remove(4));



    }
}
