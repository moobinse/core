package com.moobin.test.meta;

import com.moobin.annotation.Id;
import com.moobin.annotation.ReadOnly;
import com.moobin.annotation.bt.BtDisplay;
import com.moobin.annotation.bt.BtNumber;
import com.moobin.annotation.bt.BtText;
import com.moobin.object.MoobinObject;

@ReadOnly
public class Currency extends MoobinObject {
	
	@Id 
	@BtText(minLength=3, maxLength=3)
	public String code;

	@BtDisplay
	public String name;

	@BtNumber(min=0, max=999)
	public int currencyNumber;

	@BtNumber(min=1, max=4)
	public int mnrUnits;
	
}
