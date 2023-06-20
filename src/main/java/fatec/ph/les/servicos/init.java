package fatec.ph.les.servicos;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.reflections8.Reflections;
import org.reflections8.scanners.SubTypesScanner;

public class init {
    private static String uid = "1";
    private static Set<String> arrayList = new HashSet<>();
    private static Set<Class<?>> classes = new HashSet<>();

    static ArrayList<String> valida2 = new ArrayList<>();
    boolean t2 = true;

    public static String getUid() {
        return uid;
    }

    public static void setUid(String uid) {
        init.uid = uid;
    }

    public static Set<String> fileDirectory(String string) {

        File dir = new File(string);
        File[] directoryListing = dir.listFiles();
        System.out.println(string);
        System.out.println(Arrays.toString(directoryListing));
        // classes.addAll(findAllClassesUsingReflectionsLibrary("fatec.ph.les.servicos"));

        if (directoryListing != null) {
            for (File child : directoryListing) {

                if (!child.isDirectory()) {
                    if (!child.getAbsoluteFile().getName().contains("ServletInitializer")
                            && !child.getAbsoluteFile().getName().contains("SpringbootjspdemoApplication")
                            && child.getAbsoluteFile().getName().contains(".java")) {

                        String strings = child.getAbsolutePath().substring(string.indexOf("java") + 5,
                                string.length());

                        strings = strings.replaceAll("\\\\", ".");

                        classes.addAll(findAllClassesUsingReflectionsLibrary(strings));

                    }

                } else {

                    fileDirectory(child.getAbsolutePath().replaceAll("\\\\", "/"));

                }

            }
        }
        System.out.println("CLASSES " + classes);

        pacotes(classes);

        return arrayList;
    }

    private static void pacotes(Set<Class<?>> classes) {

        for (Class<?> classe : classes) {
            StringBuilder str2 = new StringBuilder();
            str2.append("[" + classe.getSimpleName() + " | ");
            for (Field field : classe.getDeclaredFields()) {
                int modifiers = field.getModifiers();

                if (Modifier.isProtected(modifiers)) {
                    // protected
                    str2.append(
                            " #" + field.getName() + ": " + field.getType().getSimpleName() + ";");
                } else if (Modifier.isPrivate(modifiers)) {
                    // private
                    str2.append(
                            " -" + field.getName() + ": " + field.getType().getSimpleName() + ";");
                } else {
                    str2.append(
                            " +" + field.getName() + ": " + field.getType().getSimpleName() + ";");
                }

            }
            str2.deleteCharAt(str2.length() - 1);
            str2.append(" | ");

            for (Method method : classe.getDeclaredMethods()) {
                if (!method.getName().startsWith("set") && !method.getName().startsWith("get")) {
                    str2.append(method.getName() + "();");
                }

            }
            str2.deleteCharAt(str2.length() - 1);
            str2.append("]");

            if (str2 != null) {
                arrayList.add(str2.toString());
            }

        }
    }

    public static Set<Class<?>> findAllClassesUsingReflectionsLibrary(String packageName) {
        Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
        return reflections.getSubTypesOf(Object.class)
                .stream()
                .collect(Collectors.toSet());
    }

}
