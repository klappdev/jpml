package org.kl.jpml.reflect;

import org.kl.jpml.error.PatternException;
import org.kl.jpml.meta.Sealed;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

final class SealedAttribute {
    private Class<?>[] subClasses;

    /*package-private*/ SealedAttribute(Class<?> clazz) {
        Class<?> sealedClass = clazz.getSuperclass();

        if (!sealedClass.isAnnotationPresent(Sealed.class)) {
            throw new PatternException("Checked class must to be mark as sealed");
        }

        if (!Modifier.isAbstract(sealedClass.getModifiers())) {
            throw new PatternException("Checked class must to be abstract");
        }

        try {
            final Constructor<?> constructor = sealedClass.getDeclaredConstructor();

            if (!Modifier.isPrivate(constructor.getModifiers())) {
                throw new PatternException("Default constructor must to be private");
            }

            this.subClasses = sealedClass.getClasses();

            if (subClasses.length == 0) {
                throw new PatternException("Checked class must to has one or more visible subClasses");
            }

            for (Class<?> subclass : subClasses) {
                if (!Modifier.isStatic(subclass.getModifiers())) {
                    throw new PatternException("Subclass must to be static");
                }

                if (subclass.getSuperclass() != sealedClass) {
                    throw new PatternException("Subclass must to inherit from checked class");
                }
            }
        } catch (NoSuchMethodException e) {
            throw new PatternException("Checked class must to has default constructor " + e.getMessage());
        }
    }

    /*package-private*/ Class<?>[] getSubClasses() {
        return subClasses;
    }
}
