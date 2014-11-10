package com.moobin.input.xml;

import java.util.function.Consumer;

import org.w3c.dom.Element;

class FieldTrimming<T> extends AbstractFieldMapping<T> {

	final Consumer<T> consumer;
	
	public FieldTrimming(Consumer<T> consumer) { 
		super(null);
		this.consumer = consumer;
	}

	@Override
	void apply(Element element, T item) {
		consumer.accept(item);
	}
}