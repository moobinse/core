package com.moobin.meta.bt;

import com.moobin.annotation.bt.BtNumber;
import com.moobin.meta.MetaDataField;

public class MetaNumber<T extends Number> extends AbstractMetaBusinessType<T> {

	private long min;
	
	private long max;
	
	private long step;
	
	private Class<T> javaType;
	
	public MetaNumber(MetaDataField<T, ?> field) {
		super("Number", field);
		this.javaType = field.getJavaType();
		if (javaType == Integer.class || javaType == int.class) {
			min = Integer.MIN_VALUE;
			max = Integer.MAX_VALUE;
		}
		else if (javaType == Long.class || javaType == long.class) {
			min = Long.MIN_VALUE;
			max = Long.MAX_VALUE;
		}
		else if (javaType == Byte.class || javaType == byte.class) {
			min = Byte.MIN_VALUE;
			max = Byte.MAX_VALUE;
		}
		else if (javaType == Short.class || javaType == short.class) {
			min = Short.MIN_VALUE;
			max = Short.MAX_VALUE;
		}
		step = 1;
	}
	
	public MetaNumber(MetaDataField<T, ?> field, BtNumber annotation) {
		super("Number", field);
		javaType = field.getJavaType();
		min = annotation.min();
		max = annotation.max();
		step = annotation.step();
	}
	
	public long getMin() {
		return min;
	}
	
	public long getMax() {
		return max;
	}
	
	public long getStep() {
		return step;
	}
	
	@Override
	public String toString() {
		return name() + "<" + min + "," + max + "," + step + ">";
	}
	
}
