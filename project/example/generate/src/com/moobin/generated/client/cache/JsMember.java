package com.moobin.generated.client.cache;

import com.google.gwt.core.client.*;

import com.moobin.client.CacheCallback;
import com.moobin.client.JsBase;

@SuppressWarnings("unused")
public class JsMember extends JsBase {

  protected JsMember() { }


    public final String getMemberId() {
        return get("memberId");
    }

    public final void  setMemberId(String memberId) {
        set("memberId", memberId);
    }


    public final String getName() {
        return get("name");
    }

    public final void  setName(String name) {
        set("name", name);
    }


    public final JsAddress getAdress() {
        return getObject("adress");
    }

    public final void  setAdress(JsAddress adress) {
        set("adress", adress);
    }


    public final JsArrayString getUsers() {
        return getArrayString("users");
    }
    public final void  setUsers(JsArrayString users) {
        set("users", users);
    }
}
