package com.cydeo.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


// define the annotation
@Target({ElementType.METHOD}) // what purpose serves Method
@Retention(RetentionPolicy.RUNTIME) // when serves RUNTIME
public @interface Loggable {


}
