package com.moobin.generated.client.cache;

import com.google.gwt.core.client.*;

import com.moobin.client.CacheCallback;
import com.moobin.client.JsBase;

@SuppressWarnings("unused")
public class JsCacheResponse extends JsBase {

  protected JsCacheResponse() { }


    public final int getHandle() {
        return getInteger("handle");
    }

    public final void  setHandle(int handle) {
        set("handle", handle);
    }


    public final JavaScriptObject getValue() {
        return getObject("value");
    }

    public final void  setValue(JavaScriptObject value) {
        set("value", value);
    }

}
