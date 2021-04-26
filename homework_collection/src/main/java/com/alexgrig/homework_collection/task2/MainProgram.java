package com.alexgrig.homework_collection.task2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/*
Исходные данные: текстовый файл со средней длиной строки равной 10 символам (файл или прошить текст в коде).
    В реализациях используйте наиболее подходящие имплементации коллекций!
    Задание 1: Подсчитайте количество различных слов в файле.
    Задание 2: Выведите на экран список различных слов файла, отсортированный по возрастанию их длины
    (компаратор сначала по длине слова, потом по тексту).
    Задание 3: Подсчитайте и выведите на экран сколько раз каждое слово встречается в файле.
    Задание 4: Выведите на экран все строки файла в обратном порядке.
    Задание 5: Реализуйте свой Iterator для обхода списка в обратном порядке.
    Задание 6: Выведите на экран строки, номера которых задаются пользователем в произвольном порядке.
*/
public class MainProgram {
    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("resource/test.txt");
        StringDataProvider provider = new StringDataProvider(file);

        System.out.println("Всего слов в файле: " + provider.getListWords().size());
        System.out.println("==========================================");
        System.out.println("1.Рразличных слов в файле: " + provider.getUniqueWords().size());


        System.out.println("\n==========================================\n");
        System.out.println("2.0. Cписок различных слов, отсортированный по тексту:\n");
        List<String> uniqueWords = provider.getUniqueWords();
        Collections.sort(uniqueWords);
        int k = 1;
        for (String s : uniqueWords) {
            System.out.println(k++ + " - " + s);
        }

        Collections.sort(uniqueWords, (s1, s2) -> s1.length() - s2.length());
        System.out.println("\n2.1. Cписок различных слов, отсортированный по возрастанию длины слов:");
        uniqueWords.forEach(w -> System.out.println(w));


        System.out.println("\n==========================================\n");
        System.out.println("\n3.Cколько раз каждое слово встречается в файле:");
        System.out.println("----------------------------------------------");

        System.out.println("\tСлово\t\t|\tКол-во повторов");
        System.out.println("----------------------------------------------");

        for (Map.Entry<String, Integer> pair : provider.getMapWordCounter().entrySet()) {
            System.out.printf("%10s\t\t|\t%6d", pair.getKey(), pair.getValue());
            System.out.println();
        }

        System.out.println("\n==========================================\n");
        System.out.println("\n4.Вывод на экран всех строк в обратном порядке.:\n");

        List<String> lines = provider.getLines();
        for (int i = 0; i < lines.size(); i++) {
            String str = lines.get(i);
            for (int j = str.length() - 1; j >= 0; j--) {
                System.out.print(str.charAt(j));
            }
            System.out.println();
        }

        System.out.println("\n==========================================\n");
        System.out.println("\n5. Реализуйте свой Iterator для обхода списка в обратном порядке.:\n");

        MyIterator<String> it = provider.iterator();
        while (it.hasPrevious()) {
            System.out.println(": " + it.previous());
        }

        System.out.println("\n==========================================\n");
        System.out.println("\n6. Выведите на экран строки, номера которых задаются пользователем в произвольном порядке.:\n");
        for (String s : provider.getLinesByNumbers(16, 1, 3)) {
            System.out.println(s);
        }
    }
}
