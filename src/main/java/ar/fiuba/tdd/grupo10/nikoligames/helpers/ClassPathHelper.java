package ar.fiuba.tdd.grupo10.nikoligames.helpers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ClassPathHelper {

    private static final Map<String,String> classesPackage = new HashMap<>();

    static {
        try {
            for (Class cl : getClasses("ar.fiuba.tdd.grupo10.nikoligames")) {
                classesPackage.put(cl.getSimpleName(),cl.getPackage().getName());
            }
        } catch  (Exception exc) {
            exc.printStackTrace();
        }
    }

    public static Map<String,String> getClasesPath() {
        return classesPackage;
    }

    private static Class[] getClasses(String packageName)
            throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes = new ArrayList<>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes.toArray(new Class[classes.size()]);
    }

    private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        addClasses(classes, directory, packageName);
        return classes;
    }

    private static void addClasses(List<Class> classes, File directory, String packageName) throws ClassNotFoundException {
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
    }
}
