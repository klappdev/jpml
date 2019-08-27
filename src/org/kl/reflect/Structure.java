package org.kl.reflect;

import org.kl.meta.Exclude;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

class Structure {
    private List<Field> members;

    /*package-private*/ Structure(Class<?> clazz)  {
        this.members = new ArrayList<>(3);

        for (final Field field : clazz.getDeclaredFields()) {
            if (!field.isAnnotationPresent(Exclude.class) && !Modifier.isStatic(field.getModifiers())) {
                members.add(field);
            }
        }
    }

    /*package-private*/  List<Field> getMembers() {
        return members;
    }
}
