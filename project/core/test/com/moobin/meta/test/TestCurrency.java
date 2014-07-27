package com.moobin.meta.test;

import com.moobin.annotation.Display;
import com.moobin.annotation.Id;
import com.moobin.annotation.Unique;

public class TestCurrency {

	@Id
	@Display
	public String code;
	
	@Unique
	public int id;
	
}
