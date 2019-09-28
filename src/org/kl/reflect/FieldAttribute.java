package org.kl.reflect;

import org.kl.meta.Exclude;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

final class FieldAttribute {
    private List<Field> fields;

    /*package-private*/ FieldAttribute(Class<?> clazz)  {
        this.fields = new ArrayList<>(3);

        for (final Field field : clazz.getDeclaredFields()) {
            if (!field.isAnnotationPresent(Exclude.class) && !Modifier.isStatic(field.getModifiers())) {
                fields.add(field);
            }
        }
    }

    /*package-private*/ List<Field> getFields() {
        return fields;
    }
}
