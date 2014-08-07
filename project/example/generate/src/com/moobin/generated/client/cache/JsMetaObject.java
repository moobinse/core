package com.moobin.generated.client.cache;

import com.google.gwt.core.client.*;

import com.moobin.client.CacheCallback;
import com.moobin.client.JsBase;

@SuppressWarnings("unused")
public class JsMetaObject extends JsBase {


    public String getName() {
        return get("name");
    }

    public void  setName(String name) {
        set("name", name);
    }


    public JsMetaField getFields() {
        return getObject("fields");
    }

    public void  setFields(JsMetaField fields) {
        set("fields", fields);
    }

}
