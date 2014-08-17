package com.moobin.generated.client.cache;

import com.google.gwt.core.client.*;

import com.moobin.client.CacheCallback;
import com.moobin.client.JsBase;

@SuppressWarnings("unused")
public class JsPollRequest extends JsBase {

  protected JsPollRequest() { }


    public final JsArray<JsCacheRequest> getCacheRequests() {
        return getArray("cacheRequests");
    }
    public final void  setCacheRequests(JsArray<JsCacheRequest> cacheRequests) {
        set("cacheRequests", cacheRequests);
    }
}
