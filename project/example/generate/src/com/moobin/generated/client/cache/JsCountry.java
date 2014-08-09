package com.moobin.generated.client.cache;

import com.google.gwt.core.client.*;

import com.moobin.client.CacheCallback;
import com.moobin.client.JsBase;

@SuppressWarnings("unused")
public class JsCountry extends JsBase {

  protected JsCountry() { }


    public final String getName() {
        return get("name");
    }

    public final void  setName(String name) {
        set("name", name);
    }


    public final JsArrayString getCurrencies() {
        return getArrayString("currencies");
    }
    public final void  setCurrencies(JsArrayString currencies) {
        set("currencies", currencies);
    }
}
