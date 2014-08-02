package com.moobin.annotation.bt;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BtNumber {

	long min() default Integer.MIN_VALUE;

	long max() default Integer.MAX_VALUE;

	long step() default 1; 

}
