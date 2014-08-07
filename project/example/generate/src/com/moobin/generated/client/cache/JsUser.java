package com.moobin.generated.client.cache;

import com.google.gwt.core.client.*;

import com.moobin.client.CacheCallback;
import com.moobin.client.JsBase;

@SuppressWarnings("unused")
public class JsUser extends JsBase {


    public String getUserId() {
        return get("userId");
    }

    public void  setUserId(String userId) {
        set("userId", userId);
    }


    public String getMember() {
        return get("member");
    }

    public void  setMember(String member) {
        set("member", member);
    }

    public void getMember(CacheCallback<JsMember> callback) {
        get("Member", getMember(), callback);
    }


    public String getName() {
        return get("name");
    }

    public void  setName(String name) {
        set("name", name);
    }

}
