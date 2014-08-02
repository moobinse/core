package com.moobin.meta.bt;

import com.moobin.annotation.bt.BtText;
import com.moobin.meta.MetaDataField;

public class MetaText extends AbstractMetaBusinessType<String> {

	private final int minLength;
	
	private final int maxLength;
	
	private final String regExp;

	public MetaText(MetaDataField<String, ?> field) {
		super("Text", field);
		minLength = 0;
		maxLength = 32;
		regExp = "";
	}

	public MetaText(MetaDataField<String, ?> field, BtText annotation) {
		super("Text", field);
		minLength = annotation.minLength();
		maxLength = annotation.maxLength();
		regExp = annotation.regExp();
	}
	
	public int getMaxLength() {
		return maxLength;
	}
	
	public int getMinLength() {
		return minLength;
	}
	
	public String getRegExp() {
		return regExp;
	}
	
	@Override
	public String toString() {
		if (regExp.isEmpty()) {
			return name() + "<" + minLength + "," + maxLength + ">";
		}
		return name() + "(" + regExp + ")";
	}
	
}
