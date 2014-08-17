package com.moobin.generated.client.cache;

import com.google.gwt.core.client.*;

import com.moobin.client.CacheCallback;
import com.moobin.client.JsBase;

@SuppressWarnings("unused")
public class JsCountry extends JsBase {

  protected JsCountry() { }


    public final String getName() {
        return get("name");
    }

    public final void  setName(String name) {
        set("name", name);
    }


    public final String getAlpha2() {
        return get("alpha2");
    }

    public final void  setAlpha2(String alpha2) {
        set("alpha2", alpha2);
    }


    public final String getAlpha3() {
        return get("alpha3");
    }

    public final void  setAlpha3(String alpha3) {
        set("alpha3", alpha3);
    }


    public final int getCountryNumber() {
        return getInteger("countryNumber");
    }

    public final void  setCountryNumber(int countryNumber) {
        set("countryNumber", countryNumber);
    }


    public final String getIso3166_2() {
        return get("iso3166_2");
    }

    public final void  setIso3166_2(String iso3166_2) {
        set("iso3166_2", iso3166_2);
    }


    public final Integer getRegion() {
        return getInteger("region");
    }

    public final void  setRegion(Integer region) {
        set("region", region);
    }


    public final Integer getSubRegion() {
        return getInteger("subRegion");
    }

    public final void  setSubRegion(Integer subRegion) {
        set("subRegion", subRegion);
    }

}
