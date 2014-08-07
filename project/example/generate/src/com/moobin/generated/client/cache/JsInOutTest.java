package com.moobin.generated.client.cache;

import com.google.gwt.core.client.*;

import com.moobin.client.CacheCallback;
import com.moobin.client.JsBase;

@SuppressWarnings("unused")
public class JsInOutTest extends JsBase {


    public String getId() {
        return get("id");
    }

    public void  setId(String id) {
        set("id", id);
    }


    public String getName() {
        return get("name");
    }

    public void  setName(String name) {
        set("name", name);
    }


    public int getNumber() {
        return getInteger("number");
    }

    public void  setNumber(int number) {
        set("number", number);
    }


    public JsArrayInteger getInts() {
        return getArrayInteger("ints");
    }
    public void  setInts(JsArrayInteger ints) {
        set("ints", ints);
    }

    public boolean getBoo() {
        return getBoolean("boo");
    }

    public void  setBoo(boolean boo) {
        set("boo", boo);
    }


    public JsArrayBoolean getBooleans() {
        return getArrayBoolean("booleans");
    }
    public void  setBooleans(JsArrayBoolean booleans) {
        set("booleans", booleans);
    }

    public String getCcy() {
        return get("ccy");
    }

    public void  setCcy(String ccy) {
        set("ccy", ccy);
    }

    public void getCcy(CacheCallback<JsCurrency> callback) {
        get("Currency", getCcy(), callback);
    }


    public JsInOutTest getChild() {
        return getObject("child");
    }

    public void  setChild(JsInOutTest child) {
        set("child", child);
    }

}
