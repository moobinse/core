package com.moobin.client;

public class Filter {

	final String f;
	
	public Filter(String expression) {
		f = expression;
	}
	
	static Filter equals(String field, String value) {
		return new Filter(field + "=" + value);
	}

	static Filter notEquals(String field, String value) {
		return new Filter(field + "!=" + value);
	}
	
	static Filter greaterThan(String field, String value) {
		return new Filter(field + ">" + value);
	}

	static Filter lessThan(String field, String value) {
		return new Filter(field + "<" + value);
	}

	static Filter or(String e1, String e2) {
		return new Filter("(" + e1 + ") or (" + e2 + ")");
	}
	static Filter or(Filter f1, Filter f2) {
		return or(f1.toString(), f2.toString());
	}
	
	Filter or(String e) {
		return or(f, e);
	}
	
	@Override
	public String toString() {
		return f;
	}
	
	public static void main(String[] args) {
		System.out.println(new Filter("userId=U99").or("userId=U98"));
		System.out.println(new Filter("(userId=U99) or (userId=U98)"));
		System.out.println(Filter.or("userId=U99", "userId=U98"));
	}
}
