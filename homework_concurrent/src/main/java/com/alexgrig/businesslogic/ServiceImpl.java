package com.alexgrig.businesslogic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServiceImpl implements Service {
    @Override
    public List<String> run(String item, double value, Date date) {
        try {
            Thread.sleep(1500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> list = new ArrayList<>();
        int size = (int) value;
        list.add(date.toString());
        for (int i = 1; i < size; i++) {
            list.add(item + i + value);
        }
        return list;
    }

    @Override
    public List<String> work(String item, int count) {
        try {
            Thread.sleep(1500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(item + i);
        }
        return list;
    }
}

