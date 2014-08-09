package com.moobin.generated.client.cache;

import com.google.gwt.core.client.*;

import com.moobin.client.CacheCallback;
import com.moobin.client.JsBase;

@SuppressWarnings("unused")
public class JsUser extends JsBase {

  protected JsUser() { }


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


    public final String getOrganisation() {
        return get("organisation");
    }

    public final void  setOrganisation(String organisation) {
        set("organisation", organisation);
    }

    public final void getOrganisation(CacheCallback<JsMember> callback) {
        get("Member", getOrganisation(), callback);
    }

}
