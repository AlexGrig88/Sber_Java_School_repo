package com.alexgrig;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class PluginManager {

    private final String pluginRootDirectory;

    public PluginManager(String pluginRootDirectory) {
        this.pluginRootDirectory = pluginRootDirectory;
    }

    public Plugin load(String pluginName, String pluginClassName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        try {
            URLClassLoader pluginClassLoader = new URLClassLoader(new URL[]{new URL(pluginRootDirectory)});
            Class clazz = pluginClassLoader.loadClass(pluginClassName);
            Object obj = clazz.getConstructor(String.class).newInstance(pluginName);
            if (obj instanceof Plugin) {
                return (Plugin) obj;
            } else {
                return null;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Класс-плагин по указанному пути не найден");
        }
        return null;
    }

}
