package com.desetude.necessities.configurate;

import com.google.inject.BindingAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@BindingAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface InjectConfig {

    /**
     * Returns the file name for the {@link Config}, offsetted from the
     * supplied {@link ConfigFactory}'s dir.
     *
     * @return the config file name
     */
    String value();

    /**
     * If {@code true}, the internal resource with the file name {@link this#value()}
     * will be copied into the the {@link ConfigFactory}'s directory.
     *
     * @return whether the internal resource should be copied
     */
    boolean copyResource() default true;

}