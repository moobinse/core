package com.moobin.generated.client.cache;

import com.google.gwt.core.client.*;

import com.moobin.client.CacheCallback;
import com.moobin.client.JsBase;

@SuppressWarnings("unused")
public class JsUser extends JsBase {

  protected JsUser() { }


    public final String getUserId() {
        return get("userId");
    }

    public final void  setUserId(String userId) {
        set("userId", userId);
    }


    public final String getMember() {
        return get("member");
    }

    public final void  setMember(String member) {
        set("member", member);
    }

    public final void getMember(CacheCallback<JsMember> callback) {
        get("Member", getMember(), callback);
    }


    public final String getName() {
        return get("name");
    }

    public final void  setName(String name) {
        set("name", name);
    }

}
