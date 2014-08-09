package com.moobin.generated.client.cache;

import com.google.gwt.core.client.*;

import com.moobin.client.CacheCallback;
import com.moobin.client.JsBase;

@SuppressWarnings("unused")
public class JsCurrency extends JsBase {

  protected JsCurrency() { }


    public final String getCode() {
        return get("code");
    }

    public final void  setCode(String code) {
        set("code", code);
    }


    public final String getName() {
        return get("name");
    }

    public final void  setName(String name) {
        set("name", name);
    }


    public final int getCurrencyNumber() {
        return getInteger("currencyNumber");
    }

    public final void  setCurrencyNumber(int currencyNumber) {
        set("currencyNumber", currencyNumber);
    }


    public final int getMnrUnits() {
        return getInteger("mnrUnits");
    }

    public final void  setMnrUnits(int mnrUnits) {
        set("mnrUnits", mnrUnits);
    }


    public final JsArrayInteger getIntArr() {
        return getArrayInteger("intArr");
    }
    public final void  setIntArr(JsArrayInteger intArr) {
        set("intArr", intArr);
    }
    public final void  setIntArr(int... intArr) {
        JsArrayInteger arr = createArray().cast();
        for (int i : intArr) arr.push(i);
        setIntArr(arr);
    }
}
