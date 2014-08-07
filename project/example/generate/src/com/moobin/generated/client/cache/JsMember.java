package com.moobin.generated.client.cache;

import com.google.gwt.core.client.*;

import com.moobin.client.CacheCallback;
import com.moobin.client.JsBase;

@SuppressWarnings("unused")
public class JsMember extends JsBase {


    public String getMemberId() {
        return get("memberId");
    }

    public void  setMemberId(String memberId) {
        set("memberId", memberId);
    }


    public String getName() {
        return get("name");
    }

    public void  setName(String name) {
        set("name", name);
    }


    public JsAddress getAdress() {
        return getObject("adress");
    }

    public void  setAdress(JsAddress adress) {
        set("adress", adress);
    }


    public JsArrayString getUsers() {
        return getArrayString("users");
    }
    public void  setUsers(JsArrayString users) {
        set("users", users);
    }
}
