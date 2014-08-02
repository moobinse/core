package com.moobin.meta.test;

import com.moobin.annotation.Id;
import com.moobin.annotation.Unique;
import com.moobin.annotation.bt.BtReference;

public class TestItem {

	@Id
	public String id;
	
	@Unique
	public String id2;
	
	@BtReference(TestCurrency.class)
	public String currency;
	
	@BtReference(TestCurrency.class)
	public String[] currencies;
	
	@Override
	public String toString() {
		return id;
	}
	
}
