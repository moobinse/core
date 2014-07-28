package com.moobin.test.input;

import com.moobin.annotation.BtDisplay;
import com.moobin.annotation.BtNumber;
import com.moobin.annotation.BtText;
import com.moobin.annotation.ReadOnly;
import com.moobin.annotation.Id;
import com.moobin.annotation.Xpath;
import com.moobin.object.MoobinObject;

@Xpath("////CcyTbl/CcyNtry")
@ReadOnly
public class Currency extends MoobinObject {
	
	@Id 
	@BtText(minLength=3, maxLength=3)
	@Xpath("Ccy")
	public String code;

	@BtDisplay
	@Xpath("CcyNm")
	public String name;

	@BtNumber(min=0, max=999)
	@Xpath("CcyNbr")
	public int currencyNumber;

	@BtNumber(min=1, max=4)
	@Xpath("CcyMnrUnts")
	public int mnrUnits;
	
}
