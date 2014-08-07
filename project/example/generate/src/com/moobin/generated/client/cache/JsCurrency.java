package com.moobin.generated.client.cache;

import com.google.gwt.core.client.*;

import com.moobin.client.CacheCallback;
import com.moobin.client.JsBase;

@SuppressWarnings("unused")
public class JsCurrency extends JsBase {


    public String getCode() {
        return get("code");
    }

    public void  setCode(String code) {
        set("code", code);
    }


    public String getName() {
        return get("name");
    }

    public void  setName(String name) {
        set("name", name);
    }


    public int getCurrencyNumber() {
        return getInteger("currencyNumber");
    }

    public void  setCurrencyNumber(int currencyNumber) {
        set("currencyNumber", currencyNumber);
    }


    public int getMnrUnits() {
        return getInteger("mnrUnits");
    }

    public void  setMnrUnits(int mnrUnits) {
        set("mnrUnits", mnrUnits);
    }

}
