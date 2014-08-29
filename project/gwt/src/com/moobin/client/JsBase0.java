package com.moobin.client;


public class JsBase0 extends JsBase {

	protected JsBase0() {
		
	}

	public final String stringValue(String p) {
		return get(p);
	}
    
	public final Integer intValue(String p) {
		return isNull(p) ? null : getInteger(p);
	}

	public final boolean booleanValue(String p) {
		return getBoolean(p);
	}

}
