package org.wideface;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface FaceCode {
    String[] value();
    boolean ignoreCase() default false;
}
