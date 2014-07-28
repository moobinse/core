package com.moobin.meta.bt;

import com.moobin.annotation.BtDecimal;
import com.moobin.meta.MetaDataField;

public class MetaDecimal<T extends Number> extends AbstractMetaBusinessType<T> {

	private double min;
	
	private double max;
	
	private int decimals;
	
	public MetaDecimal(MetaDataField<T, ?> field, BtDecimal annotation) {
		super("Decimal", field);
		min = annotation.min();
		max = annotation.max();
		decimals = annotation.decimals();
	}
	public MetaDecimal(MetaDataField<T, ?> field) {
		super("Decimal", field);
		if (field.getJavaType() == Double.class || field.getJavaType() == double.class) {
			min = Double.MIN_VALUE;
			max = Double.MAX_VALUE;
		}
		else if (field.getJavaType() == Float.class || field.getJavaType() == float.class) {
			min = Float.MIN_VALUE;
			max = Float.MAX_VALUE;
		}
		decimals = 3;
	}
	
	public double getMin() {
		return min;
	}
	
	public double getMax() {
		return max;
	}
	
	public int getDecimals() {
		return decimals;
	}
	
	
}
