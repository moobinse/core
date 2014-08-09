package com.moobin.generated.client.cache;

import com.google.gwt.core.client.*;

import com.moobin.client.CacheCallback;
import com.moobin.client.JsBase;

@SuppressWarnings("unused")
public class JsInOutTest extends JsBase {

  protected JsInOutTest() { }


    public final String getId() {
        return get("id");
    }

    public final void  setId(String id) {
        set("id", id);
    }


    public final String getName() {
        return get("name");
    }

    public final void  setName(String name) {
        set("name", name);
    }


    public final int getNumber() {
        return getInteger("number");
    }

    public final void  setNumber(int number) {
        set("number", number);
    }


    public final JsArrayInteger getInts() {
        return getArrayInteger("ints");
    }
    public final void  setInts(JsArrayInteger ints) {
        set("ints", ints);
    }
    public final void  setInts(int... ints) {
        JsArrayInteger arr = createArray().cast();
        for (int i : ints) arr.push(i);
	      setInts(arr);
    }

    public final boolean getBoo() {
        return getBoolean("boo");
    }

    public final void  setBoo(boolean boo) {
        set("boo", boo);
    }


    public final JsArrayBoolean getBooleans() {
        return getArrayBoolean("booleans");
    }
    public final void  setBooleans(JsArrayBoolean booleans) {
        set("booleans", booleans);
    }

    public final String getCcy() {
        return get("ccy");
    }

    public final void  setCcy(String ccy) {
        set("ccy", ccy);
    }

    public final void getCcy(CacheCallback<JsCurrency> callback) {
        get("Currency", getCcy(), callback);
    }


    public final JsInOutTest getChild() {
        return getObject("child");
    }

    public final void  setChild(JsInOutTest child) {
        set("child", child);
    }

}
