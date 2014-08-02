package com.moobin.meta;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import com.moobin.annotation.Required;
import com.moobin.annotation.Unique;
import com.moobin.annotation.bt.BtDecimal;
import com.moobin.annotation.bt.BtNumber;
import com.moobin.annotation.bt.BtReference;
import com.moobin.annotation.bt.BtText;
import com.moobin.core.MoobinException;
import com.moobin.meta.bt.MetaDecimal;
import com.moobin.meta.bt.MetaEnum;
import com.moobin.meta.bt.MetaNumber;
import com.moobin.meta.bt.MetaReference;
import com.moobin.meta.bt.MetaText;

public class MetaDataFieldImpl<F,T> implements MetaDataField<F, T> {

	private String objectName;
	private String fieldName;
	private Type type;
	private boolean unique;
	private boolean array;
	private boolean required;
	private MetaBusinessType<?> businessType;
	
	private Class<?> javaType;
	private Field field;

	private static final List<Class<?>> numberTypes = Arrays.asList(Integer.class, int.class, Long.class, long.class, Byte.class, byte.class, Short.class, short.class);
	private static final List<Class<?>> decimalTypes = Arrays.asList(Double.class, double.class, Float.class, float.class);
	
	static List<Class<?>> simpleTypes = Arrays.asList(
			String.class, 
			boolean.class, Boolean.class,
			int.class, Integer.class,
			long.class, Long.class);
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	MetaDataFieldImpl(MetaDataObject<T> parent, Field field) {
		this.field = field;
		objectName = parent.getName();
		fieldName = field.getName();
		Class<?> datatype = field.getType();
		array = datatype.isArray();
		javaType = array ? datatype.getComponentType() : datatype;
		unique = field.getAnnotation(Unique.class) != null;
		required = field.getAnnotation(Required.class) != null;
		if (simpleTypes.contains(javaType)) {
			type = array ? Type.SIMPLE_ARRAY : Type.SIMPLE;
		}
		else {
			type = array ? Type.OBJECT_ARRAY : Type.OBJECT;
		}
		// find business type
		BtNumber numberAnnotation = field.getAnnotation(BtNumber.class);
		BtText textAnnotation = field.getAnnotation(BtText.class);
		BtDecimal decimalAnnotation = field.getAnnotation(BtDecimal.class);
		BtReference referenceAnnotation = field.getAnnotation(BtReference.class);
		// TODO: test for multiple business type annotations
		if (numberAnnotation != null) {
			setBusinessType(new MetaNumber(this, numberAnnotation));
		}
		else if (decimalAnnotation != null) {
			setBusinessType(new MetaDecimal(this, decimalAnnotation));
		}
		else if (textAnnotation != null) {
			setBusinessType(new MetaText((MetaDataField<String, ?>) this, textAnnotation));
		}
		else if (referenceAnnotation != null) {
			setBusinessType(new MetaReference(this, referenceAnnotation));
		}
		else if (numberTypes.contains(javaType)) {
			setBusinessType(new MetaNumber(this));
		}
		else if (decimalTypes.contains(javaType)) {
			setBusinessType(new MetaDecimal(this));
		}
		else if (javaType == String.class) {
			setBusinessType(new MetaText((MetaDataField<String, ?>) this));
		}
		else if (Enum.class.isAssignableFrom(javaType)) {
			setBusinessType(new MetaEnum(this));
		}
		System.out.println(this);
	}
	
	private void setBusinessType(MetaBusinessType<?> businessType) {
		assert this.businessType == null;
		this.businessType = businessType;
	}
	
	@Override
	public String getName() {
		return fieldName;
	}
	
	@Override
	public Type getType() {
		return type;
	}
	
	@Override
	public boolean isRequired() {
		return required;
	}
	
	@Override
	public boolean isArray() {
		return array;
	}
	
	@Override
	public boolean isUnique() {
		return unique;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<F> getJavaType() {
		return (Class<F>) javaType;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public F get(T item) {
		try {
			return (F) field.get(item);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new MoobinException(e);
		}
	}

	@Override
	public void set(T item, String value) {
		if (value == null) {
			return;
		}
		try {
			if (field.getType() == String.class) {
				field.set(item, value);
			}
			if (field.getType() == Integer.class || field.getType() == int.class) {
				field.set(item, Integer.valueOf(value));
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new MoobinException(e);
		}
	}

	@Override
	public Function<T, F> getFunction() {
		return (t) -> get(t);
	}
	
	@Override
	public String toString() {
		return objectName + "." + fieldName + "[" + businessType + "]";
	}

}
