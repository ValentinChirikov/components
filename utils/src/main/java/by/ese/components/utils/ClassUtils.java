/*
 * (C) Copyright 30 окт. 2018 г. Valentin Chirikov (http://ese.by/) 
 * valc@ese.by +375 44 7629763
 */
package by.ese.components.utils;

import java.lang.reflect.Field;

/**
 *
 * @author Valentin
 */
public class ClassUtils {

    /**
     * Convert "niceNameMethod" to "Nice name method"
     *
     * @param name
     * @return
     */
    public static String nicify(String name) {
        StringBuilder sb = new StringBuilder();
        if (name.length() > 1) {
            sb.append(Character.toUpperCase(name.charAt(0)));
            for (int i = 1; i < name.length(); i++) {
                if (Character.isUpperCase(name.charAt(i))) {
                    sb.append(" ").append(Character.toLowerCase(name.charAt(i)));
                } else {
                    sb.append(name.charAt(i));
                }
            }
            return sb.toString();
        } else {
            return name;
        }
    }

    private static Field searchField(Field[] fields, String name) {
        for (Field field : fields) {
            if (field.getName().equals(name)) {
                return field;
            }
        }
        return null;
    }

    /**
     * @deprecated @see com.querydsl.core.util.ReflectionUtils or spring
     * findField
     * @param clazz
     * @param name
     * @return
     * @throws NoSuchFieldException
     */
    public static Field getField(Class clazz, String name) throws NoSuchFieldException {
        Class searchableClass = clazz;
        Field field;

        field = searchField(searchableClass.getDeclaredFields(), name);

        if (field == null) {
            while (searchableClass.getSuperclass() != null && field == null) {
                searchableClass = searchableClass.getSuperclass();
                field = searchField(searchableClass.getDeclaredFields(), name);
            }
        }

        if (field == null) {
            throw new NoSuchFieldException();
        } else {
            return field;
        }

    }

}
