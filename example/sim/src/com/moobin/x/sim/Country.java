package com.moobin.x.sim;

import com.moobin.annotation.Id;
import com.moobin.annotation.ReadOnly;
import com.moobin.annotation.Unique;
import com.moobin.annotation.bt.BtDisplay;
import com.moobin.annotation.bt.BtNumber;

@ReadOnly
public class Country {

	@Id
	@BtDisplay
	public String name;

	@Unique
	public String alpha2;
	
	@Unique
	public String alpha3;

	@Unique
	@BtNumber(min=0, max=10000)
	public int countryNumber;

	@Unique
	public String iso3166_2;

	@BtNumber(min=0, max=100)
	public Integer region;
	
	@BtNumber(min=0, max=1000)
	public Integer subRegion;
	
	@Override
	public String toString() {
		return name;
	}
	
}
