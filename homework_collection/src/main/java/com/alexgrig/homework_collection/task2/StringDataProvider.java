package com.alexgrig.homework_collection.task2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class StringDataProvider implements Iterable<String> {

    private String[] words;
    private String originalText;
    private String clearText;
    private File file;

    public StringDataProvider(File file) throws FileNotFoundException {
        this.file = file;
        if (file.exists()) {
            try (FileInputStream fin = new FileInputStream(file)) {
                byte[] buffer = new byte[fin.available()];
                fin.read(buffer, 0, fin.available());
                originalText = new String(buffer);
                clearText = originalText.replaceAll("[0-9.,;:!?(){}\"\"-]", "").toLowerCase(Locale.ROOT);
                words = clearText.split("\\s+");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new FileNotFoundException("Error. Такого файла не существует. Создайте текстовый файл и попробуйте снова.");
        }
    }

    public List<String> getListWords() {
        return Arrays.asList(words);
    }


    public List<String> getLines() {
        List<String> lines = new ArrayList<>();
        Scanner scanner = new Scanner(originalText);
        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }
        return lines;
    }


    public List<String> getUniqueWords() {
        Set<String> uniqueWords = new HashSet<>(getListWords());
        return new ArrayList<>(uniqueWords);
    }

    public Map<String, Integer> getMapWordCounter() {
        Map<String, Integer> map = new HashMap<>();

        int len = words.length;
        for (int i = 0; i < len; i++) {
            if (map.containsKey(words[i])) {
                map.replace(words[i], map.get(words[i]) + 1);
            } else {
                map.put(words[i], 1);
            }
        }
        return map;
    }


    @Override
    public MyIterator<String> iterator() {
        return new MyIterator<String>() {

            private int currentIndex = 0;
            private int currentSize = words.length;
            private int reverseIndex = words.length - 1;

            @Override
            public boolean hasPrevious() {
                return reverseIndex >= 0 && words[currentIndex] != null;
            }

            @Override
            public String previous() {
                return words[reverseIndex--];
            }

            @Override
            public boolean hasNext() {
                return currentIndex < currentSize && words[currentIndex] != null;
            }

            @Override
            public String next() {
                return words[currentIndex++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };

    }


    public List<String> getLinesByNumbers(int... nums) {
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < getLines().size(); i++) {
            map.put(i + 1, getLines().get(i));
        }
        List<String> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= getLines().size()) {
                result.add(map.get(nums[i]));
            }
        }
        if (result.isEmpty()) System.out.println("Таких строк нет!");
        return result;
    }
}
