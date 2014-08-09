package com.moobin.generated.client.cache;

import java.util.Collection;
import java.util.List;
import com.moobin.client.CacheCallback;
import com.moobin.client.CacheSubscription;
import com.moobin.client.Moobin;

public class JsCache {

    public static void getCountry(String key, CacheCallback<JsCountry> callback) {
        Moobin.getCache().get("Country", key, callback);
    }
    public static void getCountryList(Collection<String> keys, CacheCallback<List<JsCountry>> callback) {
        Moobin.getCache().getList("Country", keys, callback);
    }
    public static void getCountryList(CacheCallback<List<JsCountry>> callback) {
        Moobin.getCache().getList("Country", callback);
    }
    public static void subscribeCountry(String key, CacheSubscription<JsCountry> subscription) {
        Moobin.getCache().subscribe("Country", subscription);
    }
    public static void getMetaObject(String key, CacheCallback<JsMetaObject> callback) {
        Moobin.getCache().get("MetaObject", key, callback);
    }
    public static void getMetaObjectList(Collection<String> keys, CacheCallback<List<JsMetaObject>> callback) {
        Moobin.getCache().getList("MetaObject", keys, callback);
    }
    public static void getMetaObjectList(CacheCallback<List<JsMetaObject>> callback) {
        Moobin.getCache().getList("MetaObject", callback);
    }
    public static void subscribeMetaObject(String key, CacheSubscription<JsMetaObject> subscription) {
        Moobin.getCache().subscribe("MetaObject", subscription);
    }
    public static void getMarketList(String key, CacheCallback<JsMarketList> callback) {
        Moobin.getCache().get("MarketList", key, callback);
    }
    public static void getMarketListList(Collection<String> keys, CacheCallback<List<JsMarketList>> callback) {
        Moobin.getCache().getList("MarketList", keys, callback);
    }
    public static void getMarketListList(CacheCallback<List<JsMarketList>> callback) {
        Moobin.getCache().getList("MarketList", callback);
    }
    public static void subscribeMarketList(String key, CacheSubscription<JsMarketList> subscription) {
        Moobin.getCache().subscribe("MarketList", subscription);
    }
    public static void getCurrency(String key, CacheCallback<JsCurrency> callback) {
        Moobin.getCache().get("Currency", key, callback);
    }
    public static void getCurrencyList(Collection<String> keys, CacheCallback<List<JsCurrency>> callback) {
        Moobin.getCache().getList("Currency", keys, callback);
    }
    public static void getCurrencyList(CacheCallback<List<JsCurrency>> callback) {
        Moobin.getCache().getList("Currency", callback);
    }
    public static void subscribeCurrency(String key, CacheSubscription<JsCurrency> subscription) {
        Moobin.getCache().subscribe("Currency", subscription);
    }
    public static void getUser(String key, CacheCallback<JsUser> callback) {
        Moobin.getCache().get("User", key, callback);
    }
    public static void getUserList(Collection<String> keys, CacheCallback<List<JsUser>> callback) {
        Moobin.getCache().getList("User", keys, callback);
    }
    public static void getUserList(CacheCallback<List<JsUser>> callback) {
        Moobin.getCache().getList("User", callback);
    }
    public static void subscribeUser(String key, CacheSubscription<JsUser> subscription) {
        Moobin.getCache().subscribe("User", subscription);
    }
    public static void getMarket(String key, CacheCallback<JsMarket> callback) {
        Moobin.getCache().get("Market", key, callback);
    }
    public static void getMarketList(Collection<String> keys, CacheCallback<List<JsMarket>> callback) {
        Moobin.getCache().getList("Market", keys, callback);
    }
    public static void getMarketList(CacheCallback<List<JsMarket>> callback) {
        Moobin.getCache().getList("Market", callback);
    }
    public static void subscribeMarket(String key, CacheSubscription<JsMarket> subscription) {
        Moobin.getCache().subscribe("Market", subscription);
    }
    public static void getMetaField(String key, CacheCallback<JsMetaField> callback) {
        Moobin.getCache().get("MetaField", key, callback);
    }
    public static void getMetaFieldList(Collection<String> keys, CacheCallback<List<JsMetaField>> callback) {
        Moobin.getCache().getList("MetaField", keys, callback);
    }
    public static void getMetaFieldList(CacheCallback<List<JsMetaField>> callback) {
        Moobin.getCache().getList("MetaField", callback);
    }
    public static void subscribeMetaField(String key, CacheSubscription<JsMetaField> subscription) {
        Moobin.getCache().subscribe("MetaField", subscription);
    }
    public static void getAccount(String key, CacheCallback<JsAccount> callback) {
        Moobin.getCache().get("Account", key, callback);
    }
    public static void getAccountList(Collection<String> keys, CacheCallback<List<JsAccount>> callback) {
        Moobin.getCache().getList("Account", keys, callback);
    }
    public static void getAccountList(CacheCallback<List<JsAccount>> callback) {
        Moobin.getCache().getList("Account", callback);
    }
    public static void subscribeAccount(String key, CacheSubscription<JsAccount> subscription) {
        Moobin.getCache().subscribe("Account", subscription);
    }
    public static void getMember(String key, CacheCallback<JsMember> callback) {
        Moobin.getCache().get("Member", key, callback);
    }
    public static void getMemberList(Collection<String> keys, CacheCallback<List<JsMember>> callback) {
        Moobin.getCache().getList("Member", keys, callback);
    }
    public static void getMemberList(CacheCallback<List<JsMember>> callback) {
        Moobin.getCache().getList("Member", callback);
    }
    public static void subscribeMember(String key, CacheSubscription<JsMember> subscription) {
        Moobin.getCache().subscribe("Member", subscription);
    }
}