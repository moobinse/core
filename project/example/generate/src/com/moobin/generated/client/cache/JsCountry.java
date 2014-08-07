package com.moobin.generated.client.cache;

import com.google.gwt.core.client.*;

import com.moobin.client.CacheCallback;
import com.moobin.client.JsBase;

@SuppressWarnings("unused")
public class JsCountry extends JsBase {


    public String getName() {
        return get("name");
    }

    public void  setName(String name) {
        set("name", name);
    }


    public JsArrayString getCurrencies() {
        return getArrayString("currencies");
    }
    public void  setCurrencies(JsArrayString currencies) {
        set("currencies", currencies);
    }
}
