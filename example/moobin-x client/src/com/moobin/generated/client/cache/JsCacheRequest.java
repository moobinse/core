package com.moobin.generated.client.cache;

import com.google.gwt.core.client.*;

import com.moobin.client.CacheCallback;
import com.moobin.client.JsBase;

@SuppressWarnings("unused")
public class JsCacheRequest extends JsBase {

  protected JsCacheRequest() { }


    public final String getType() {
        return get("type");
    }

    public final void  setType(String type) {
        set("type", type);
    }


    public final String getKey() {
        return get("key");
    }

    public final void  setKey(String key) {
        set("key", key);
    }


    public final int getHandle() {
        return getInteger("handle");
    }

    public final void  setHandle(int handle) {
        set("handle", handle);
    }

}
