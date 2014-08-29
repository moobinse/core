package com.moobin.generated.client.cache;

import com.google.gwt.core.client.*;

import com.moobin.client.CacheCallback;
import com.moobin.client.JsBase;

@SuppressWarnings("unused")
public class JsMetaObject extends JsBase {

  protected JsMetaObject() { }

    public final String getName() {
        return get("name");
    }

    public final void  setName(String name) {
        set("name", name);
    }


    public final String getKeyField() {
        return get("keyField");
    }

    public final void  setKeyField(String keyField) {
        set("keyField", keyField);
    }


    public final String getDisplayField() {
        return get("displayField");
    }

    public final void  setDisplayField(String displayField) {
        set("displayField", displayField);
    }


    public final boolean getCacheRoot() {
        return getBoolean("cacheRoot");
    }

    public final void  setCacheRoot(boolean cacheRoot) {
        set("cacheRoot", cacheRoot);
    }


    public final JsArray<JsMetaField> getFields() {
        return getArray("fields");
    }
    public final void  setFields(JsArray<JsMetaField> fields) {
        set("fields", fields);
    }
}
