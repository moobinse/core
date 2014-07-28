package com.moobin.meta.test;

import com.moobin.annotation.BtDisplay;
import com.moobin.annotation.Id;
import com.moobin.annotation.Unique;

public class TestCurrency {

	@Id
	@BtDisplay
	public String code;
	
	@Unique
	public int id;
	
}
