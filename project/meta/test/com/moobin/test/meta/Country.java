package com.moobin.test.meta;

import com.moobin.annotation.Id;
import com.moobin.annotation.ReadOnly;
import com.moobin.annotation.bt.BtDisplay;

@ReadOnly
public class Country {

	@Id
	@BtDisplay
	public String name;

	public String[] currencies;
	
}
