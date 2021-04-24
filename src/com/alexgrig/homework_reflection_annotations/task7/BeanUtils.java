package com.alexgrig.homework_reflection_annotations.task7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Scans object "from" for all getters. If object "to"
 * contains correspondent setter, it will invoke it
 * to set property value for "to" which equals to the property
 * of "from".
 * <p/>
 * The type in setter should be compatible to the value returned
 * by getter (if not, no invocation performed).
 * Compatible means that parameter type in setter should
 * be the same or be superclass of the return type of the getter.
 * <p/>
 * The method takes care only about public methods.
 * <p>
 * param to   Object which properties will be set.
 * param from Object which properties will be used to get values.
 */

public class BeanUtils {

    public static void assign(Object to, Object from) {
        Class<?> fromClazz = from.getClass();
        Class<?> toClazz = to.getClass();
        List<Method> getMethodsOfFrom = new ArrayList<>();
        List<Method> setMethodsOfTo = new ArrayList<>();
        //извлекаем из объекта все публичные геттеры и помещаем их в список
        for (Method m : fromClazz.getDeclaredMethods()) {
            if (Modifier.isPublic(m.getModifiers()) && m.getName().startsWith("get")) {
                getMethodsOfFrom.add(m);
            }
        }
        //извлекаем из объекта все публичные сеттеры и помещаем их в список
        for (Method m : toClazz.getDeclaredMethods()) {
            if (Modifier.isPublic(m.getModifiers()) && m.getName().startsWith("set")) {
                setMethodsOfTo.add(m);
            }
        }
        //пробегаем по всем геттерам и сеттерам - если они имеют
        //одинаковые имена после отбрасывания префиксов get, set
        //а также совместимые типы - производим необходимую установку
        for (Method mGet : getMethodsOfFrom) {
            String nameGetMethod = mGet.getName().substring(3);
            for (Method mSet : setMethodsOfTo) {
                if (nameGetMethod.equals(mSet.getName().substring(3))) {
                    if (isTypeCompatible(mGet, mSet)) {
                        try {
                            mSet.invoke(to, mGet.invoke(from));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

    }

    //проверяем является ли тип геттера таким же, как у сеттера, или же тип геттера
    //входит в иерархию типа сеттера
    private static boolean isTypeCompatible(Method mGet, Method mSet) {
        if (mGet.getReturnType().getName().equals(mSet.getParameterTypes()[0].getName())) {
            return true;
        } else {
            Class<?> clazzSuper = mSet.getParameterTypes()[0];
            Class<?> clazzGet = mGet.getReturnType();

            while (clazzGet != null) {
                if (!clazzGet.getName().equals(clazzSuper.getName())) {
                    clazzGet = clazzGet.getSuperclass();
                } else {
                    return true;
                }
            }
            return false;
        }
    }

}
