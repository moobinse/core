package com.moobin.meta.test;

import com.moobin.annotation.Id;
import com.moobin.annotation.Unique;
import com.moobin.annotation.bt.BtDisplay;

public class TestCurrency {

	@Id
	@BtDisplay
	public String code;
	
	@Unique
	public int id;
	
}
