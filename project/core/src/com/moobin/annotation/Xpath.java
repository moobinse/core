package com.moobin.annotation;

import java.lang.annotation.Repeatable;

@Repeatable(Xpaths.class)
public @interface Xpath {

	String value();
	
}
