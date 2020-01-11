package org.hebrbot.bot.utils;

import java.lang.reflect.Field;

public class ReflectUtils {

    public static Object readField(final Field field, final Object target, final boolean forceAccess) throws IllegalAccessException {
        if (forceAccess && !field.isAccessible()) {
            field.setAccessible(true);
        } else {
            field.setAccessible(true);
        }
        return field.get(target);
    }
}
