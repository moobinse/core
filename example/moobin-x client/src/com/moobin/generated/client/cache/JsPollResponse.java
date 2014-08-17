package com.moobin.generated.client.cache;

import com.google.gwt.core.client.*;

import com.moobin.client.CacheCallback;
import com.moobin.client.JsBase;

@SuppressWarnings("unused")
public class JsPollResponse extends JsBase {

  protected JsPollResponse() { }


    public final JsArray<JsCacheResponse> getCacheResponses() {
        return getArray("cacheResponses");
    }
    public final void  setCacheResponses(JsArray<JsCacheResponse> cacheResponses) {
        set("cacheResponses", cacheResponses);
    }
}
