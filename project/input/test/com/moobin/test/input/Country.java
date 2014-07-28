package com.moobin.test.input;

import com.moobin.annotation.BtDisplay;
import com.moobin.annotation.Id;
import com.moobin.annotation.ReadOnly;
import com.moobin.annotation.Xpath;

@ReadOnly
@Xpath("////CcyTbl/CcyNtry")
public class Country {

	@Xpath("CtryNm")
	@Id
	@BtDisplay
	public String name;

	@Xpath("../CcyNtry[CtryName=current()/CtryName]/Ccy")
	public String[] currencies;
	
}
