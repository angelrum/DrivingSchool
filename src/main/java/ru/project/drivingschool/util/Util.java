package ru.project.drivingschool.util;

import ru.project.drivingschool.model.common.HasId;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class Util {
    public static <T extends Enum<T>> T getEnumFromString(Class<T> c, String string) throws IllegalArgumentException {
        if (Objects.nonNull(c) && Objects.nonNull(string))
            return Enum.valueOf(c, string.trim().toUpperCase());
        return null;
    }
    // Создаем новый класс с типом tClass, одноименные поля переносим из класса E в экземпляр нового класса
    public static <T extends HasId, E extends HasId> T copyFieldToFieldByName(E e, Class<T> tClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        T t = tClass.getDeclaredConstructor(null).newInstance();
        for (Field f : e.getClass().getDeclaredFields()) {
            try {
                Field tField = tClass.getDeclaredField(f.getName());
                if (tField.getAnnotatedType().equals(f.getAnnotatedType())) {
                    tField.setAccessible(true);
                    f.setAccessible(true);
                    tField.set(t, f.get(e));
                }
            } catch (NoSuchFieldException | SecurityException ex) {
                //ignore
            }
        }
        t.setId(e.getId());
        return t;
    }
}
