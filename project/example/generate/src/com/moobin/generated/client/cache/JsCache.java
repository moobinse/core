package com.moobin.generated.client.cache;

import java.util.Collection;
import com.google.gwt.core.client.*;
import com.moobin.client.CacheCallback;
import com.moobin.client.CacheSubscription;
import com.moobin.client.Moobin;

public class JsCache {

    public static void getMetaField(String key, CacheCallback<JsMetaField> callback) {
        Moobin.getCache().get("MetaField", key, callback);
    }
    public static void getMetaFieldList(Collection<String> keys, CacheCallback<JsArray<JsMetaField>> callback) {
        Moobin.getCache().getList("MetaField", keys, callback);
    }
    public static void getMetaFieldList(CacheCallback<JsArray<JsMetaField>> callback) {
        Moobin.getCache().getList("MetaField", callback);
    }
    public static void subscribeMetaField(String key, CacheSubscription<JsMetaField> subscription) {
        Moobin.getCache().subscribe("MetaField", subscription);
    }
    public static void getUser(String key, CacheCallback<JsUser> callback) {
        Moobin.getCache().get("User", key, callback);
    }
    public static void getUserList(Collection<String> keys, CacheCallback<JsArray<JsUser>> callback) {
        Moobin.getCache().getList("User", keys, callback);
    }
    public static void getUserList(CacheCallback<JsArray<JsUser>> callback) {
        Moobin.getCache().getList("User", callback);
    }
    public static void subscribeUser(String key, CacheSubscription<JsUser> subscription) {
        Moobin.getCache().subscribe("User", subscription);
    }
    public static void getMember(String key, CacheCallback<JsMember> callback) {
        Moobin.getCache().get("Member", key, callback);
    }
    public static void getMemberList(Collection<String> keys, CacheCallback<JsArray<JsMember>> callback) {
        Moobin.getCache().getList("Member", keys, callback);
    }
    public static void getMemberList(CacheCallback<JsArray<JsMember>> callback) {
        Moobin.getCache().getList("Member", callback);
    }
    public static void subscribeMember(String key, CacheSubscription<JsMember> subscription) {
        Moobin.getCache().subscribe("Member", subscription);
    }
    public static void getCountry(String key, CacheCallback<JsCountry> callback) {
        Moobin.getCache().get("Country", key, callback);
    }
    public static void getCountryList(Collection<String> keys, CacheCallback<JsArray<JsCountry>> callback) {
        Moobin.getCache().getList("Country", keys, callback);
    }
    public static void getCountryList(CacheCallback<JsArray<JsCountry>> callback) {
        Moobin.getCache().getList("Country", callback);
    }
    public static void subscribeCountry(String key, CacheSubscription<JsCountry> subscription) {
        Moobin.getCache().subscribe("Country", subscription);
    }
    public static void getMetaObject(String key, CacheCallback<JsMetaObject> callback) {
        Moobin.getCache().get("MetaObject", key, callback);
    }
    public static void getMetaObjectList(Collection<String> keys, CacheCallback<JsArray<JsMetaObject>> callback) {
        Moobin.getCache().getList("MetaObject", keys, callback);
    }
    public static void getMetaObjectList(CacheCallback<JsArray<JsMetaObject>> callback) {
        Moobin.getCache().getList("MetaObject", callback);
    }
    public static void subscribeMetaObject(String key, CacheSubscription<JsMetaObject> subscription) {
        Moobin.getCache().subscribe("MetaObject", subscription);
    }
    public static void getInOutTest(String key, CacheCallback<JsInOutTest> callback) {
        Moobin.getCache().get("InOutTest", key, callback);
    }
    public static void getInOutTestList(Collection<String> keys, CacheCallback<JsArray<JsInOutTest>> callback) {
        Moobin.getCache().getList("InOutTest", keys, callback);
    }
    public static void getInOutTestList(CacheCallback<JsArray<JsInOutTest>> callback) {
        Moobin.getCache().getList("InOutTest", callback);
    }
    public static void subscribeInOutTest(String key, CacheSubscription<JsInOutTest> subscription) {
        Moobin.getCache().subscribe("InOutTest", subscription);
    }
    public static void getAddress(String key, CacheCallback<JsAddress> callback) {
        Moobin.getCache().get("Address", key, callback);
    }
    public static void getAddressList(Collection<String> keys, CacheCallback<JsArray<JsAddress>> callback) {
        Moobin.getCache().getList("Address", keys, callback);
    }
    public static void getAddressList(CacheCallback<JsArray<JsAddress>> callback) {
        Moobin.getCache().getList("Address", callback);
    }
    public static void subscribeAddress(String key, CacheSubscription<JsAddress> subscription) {
        Moobin.getCache().subscribe("Address", subscription);
    }
    public static void getCurrency(String key, CacheCallback<JsCurrency> callback) {
        Moobin.getCache().get("Currency", key, callback);
    }
    public static void getCurrencyList(Collection<String> keys, CacheCallback<JsArray<JsCurrency>> callback) {
        Moobin.getCache().getList("Currency", keys, callback);
    }
    public static void getCurrencyList(CacheCallback<JsArray<JsCurrency>> callback) {
        Moobin.getCache().getList("Currency", callback);
    }
    public static void subscribeCurrency(String key, CacheSubscription<JsCurrency> subscription) {
        Moobin.getCache().subscribe("Currency", subscription);
    }
}