package com.alexgrig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calculator implements Calculable {

    @Override
    public List<Integer> fibonachi(int n) {
        System.out.println("Очень сложные и долгие вычисления!");
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int n1 = 0;
        int n2 = 1;
        int n3 = 0;
        List<Integer> result = new ArrayList<>(Arrays.asList(n1, n2));
        for (int i = 2; i < n; i++) {
            n3 = n1 + n2;
            result.add(n3);
            n1 = n2;
            n2 = n3;

        }
        return result;
    }
}
