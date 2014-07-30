package com.moobin.test.example;

import com.moobin.annotation.BtDisplay;
import com.moobin.annotation.BtNumber;
import com.moobin.annotation.BtText;
import com.moobin.annotation.Id;
import com.moobin.annotation.ReadOnly;
import com.moobin.annotation.Xpath;
import com.moobin.object.MoobinObject;

@Xpath("//CcyTbl/CcyNtry")
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

	@Override
	public String toString() {
		return currencyNumber + " " + name;
	}
}
