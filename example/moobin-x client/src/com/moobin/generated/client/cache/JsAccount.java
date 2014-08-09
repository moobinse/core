package com.moobin.generated.client.cache;

import com.google.gwt.core.client.*;

import com.moobin.client.CacheCallback;
import com.moobin.client.JsBase;

@SuppressWarnings("unused")
public class JsAccount extends JsBase {

  protected JsAccount() { }


    public final String getAccountId() {
        return get("accountId");
    }

    public final void  setAccountId(String accountId) {
        set("accountId", accountId);
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
