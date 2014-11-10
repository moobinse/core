package com.moobin.input.xml;

import java.util.function.BiFunction;

import org.w3c.dom.Element;

import com.moobin.meta.MetaDataField;

class FieldBiFunction<T> extends AbstractFieldMapping<T> {

	final BiFunction<T, Element, String> function;
	public FieldBiFunction(MetaDataField<?, T> field, BiFunction<T, Element, String> function) {
		super(field);
		this.function = function;
	}
	@Override
	void apply(Element element, T item) {
		set(item, function.apply(item, element));
	}
}