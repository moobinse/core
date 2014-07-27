package com.moobin.meta.test;

import com.moobin.annotation.Id;
import com.moobin.annotation.Unique;

public class TestItem {

	@Id
	public String id;
	
	@Unique
	public String id2;
	
	@Reference(TestCurrency.class)
	public String currency;
	
	@Reference(TestCurrency.class)
	public String[] currencies;
	
	
}
