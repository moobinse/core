package com.moobin.annotation;

public @interface BtText {

	int minLength() default 0;

	int maxLength();

	String regExp() default "";
}
