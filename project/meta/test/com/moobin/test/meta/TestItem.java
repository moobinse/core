package com.moobin.test.meta;

import com.moobin.annotation.BtNumber;
import com.moobin.annotation.Id;
import com.moobin.annotation.BtReference;
import com.moobin.annotation.Unique;
import com.moobin.meta.test.TestCurrency;

public class TestItem {

	@Id
	public String id;
	
	@Unique
	public String id2;
	
	@BtReference(TestCurrency.class)
	public String currency;
	
	@BtReference(TestCurrency.class)
	public String[] currencies;
	
	@BtNumber(min=0, max=100)
	public int intValue; 

	public TestEnum enumValue;
	
}
