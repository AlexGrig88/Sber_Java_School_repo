package com.alexgrig.task1;

import java.io.File;
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

        URLClassLoader pluginClassLoader = null;
        try {
            File dir = new File(pluginRootDirectory + "\\" + pluginName);
            pluginClassLoader = new URLClassLoader(new URL[]{dir.toURI().toURL()});
            Class clazz = pluginClassLoader.loadClass(pluginClassName);
            Object obj = clazz.getConstructor().newInstance();
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
