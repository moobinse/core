package com.moobin.x.sim;

import com.moobin.annotation.Id;
import com.moobin.annotation.Unique;

public class Market {

	@Id
	public String id;
	
	@Unique
	public String name;
	
}
