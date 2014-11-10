package com.moobin.input.xml;

import java.util.function.Function;

import org.w3c.dom.Element;

import com.moobin.meta.MetaDataField;

class FieldFunction<T> extends AbstractFieldMapping<T> {

	final Function<T, String> function;
	public FieldFunction(MetaDataField<?, T> field, Function<T, String> function) {
		super(field);
		this.function = function;
	}
	
	@Override
	void apply(Element element, T item) {
		set(item, function.apply(item));
	}
}