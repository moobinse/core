package com.moobin.object;

import com.moobin.annotation.Id;


public class MoobinEnum<E extends Enum<E>> extends MoobinObject {

	@Id
	public String name;

	public int ordinal;
	
	public MoobinEnum(E value) {
		name = value.name();
		ordinal = value.ordinal();
	} 

}
