package com.alexgrig.reflection_annotations.task1_2_3_4;

import com.alexgrig.reflection_annotations.CalculatorImpl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/*Задача 1:
Имплементировать следующий интерфейс в классе CalculatorImpl
 public interface Calculator{
	/**
	* Расчет факториала числа.
	* @param number

	int calc (int number);
}
  Задача 2:
Вывести на консоль все методы класса, включая все родительские методы
(включая приватные)

Задача 3:
Вывести все геттеры класса

Задача 4:
Проверить что все String константы имеют значение = их имени
public static final String MONDAY = "MONDAY";

 */
public class MainProgram {
    public static void main(String[] args) {

        try {

            Class<?> clazz = CalculatorImpl.class;
            CalculatorImpl calculator = (CalculatorImpl) clazz.getConstructor(String.class, double.class)
                    .newInstance("AwesomeCalculator", 0.003);

            System.out.println("Задача 2");
            Method[] methods = clazz.getMethods();
            System.out.println("Все public методы класса, включая родительские:");
            int i = 1;
            for (Method method : methods) {
                printFormattedResult(method, i++);
            }

            System.out.println("=========================================================");
            System.out.println("Private методы класса:");
            Method[] declMethods = clazz.getDeclaredMethods();
            i = 1;
            for (Method m : declMethods) {
                if (Modifier.isPrivate(m.getModifiers())) {
                    printFormattedResult(m, i++);
                }
            }

            System.out.println("=========================================================");
            System.out.println("Задача 3");
            System.out.println("Все геттеры класса:");
            i = 1;
            for (Method m : declMethods) {
                if (m.getName().startsWith("get")) {
                    System.out.println(i++ + ") " + m.getName());
                }
            }
            System.out.println("=========================================================");
            System.out.println("Задача 4");
            System.out.println("Проверить что все String константы имеют значение = их имени");
            System.out.println("Равны?: " + allFinalStringsFieldsAreEqual(calculator));


        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
    public static boolean allFinalStringsFieldsAreEqual(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.getType().getName().equals(String.class.getName()) && Modifier.isFinal(field.getModifiers())) {
                if (!field.getName().equals((String) field.get(clazz))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void printFormattedResult(Method m, int i) {
        System.out.println(i++ + ") " + m.getName());
        System.out.print("             Типы принимаемых параметров: ");
        for (Class<?> cl : m.getParameterTypes()) {
            System.out.print(cl.getName() + ", ");
        }
        System.out.println("\b\b");
        System.out.print("             Возвращаемое значение: " + m.getReturnType().getName());
        System.out.println();

    }
}
