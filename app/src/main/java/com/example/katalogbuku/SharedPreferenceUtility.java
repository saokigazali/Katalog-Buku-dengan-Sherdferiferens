package com.example.katalogbuku;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.katalogbuku.model.Buku;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class SharedPreferenceUtility {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;

    private static final String PREFER_NAME = "AndroidExamplePref";
    private static final String IS_USER_LOGIN = "IsUserLoggedIn";

    public static final String KEY_NAME = "name";
    public static final String KEY_ALAMAT = "alamat";
    public static final String KEY_EMAIL = "email";
    private static final String PREF_FILE = "simple.example.katalogbuku.DATA";
    private static final String TRANS_KEY = "TRANS";

    public SharedPreferenceUtility(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    public void createUserLoginSession(String Nama, String Email, String Alamat){
        editor.putBoolean(IS_USER_LOGIN, true);
        editor.putString(KEY_NAME, Nama);
        editor.putString(KEY_ALAMAT, Alamat);
        editor.putString(KEY_EMAIL, Email);
        editor.commit();
    }
    public boolean checkLogin(){
        if(!this.isUserLoggedIn()){
            Intent i = new Intent(_context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
            return true;
        }
        return false;
    }
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        user.put(KEY_ALAMAT, pref.getString(KEY_ALAMAT, null));
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        return user;
    }
    public void logoutUser(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(_context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }
    public boolean isUserLoggedIn(){
        return pref.getBoolean(IS_USER_LOGIN, false);
    }
    private static SharedPreferences getSharedPref(Context ctx) {
        SharedPreferences sharedPref = ctx.getSharedPreferences(
                PREF_FILE, Context.MODE_PRIVATE);
        return sharedPref;
    }
    public static List<Buku> getAllBuku(Context ctx) {
        String jsonString = getSharedPref(ctx).getString(TRANS_KEY, null);
        List<Buku> trs = new ArrayList<Buku>();
        if (jsonString != null) {
            Log.d("SH PREF","json string is:"+jsonString);
            try {
                JSONArray jsonArray = new JSONArray(jsonString);
                for (int i=0;i<jsonArray.length();i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    trs.add(Buku.fromJSONObject(obj));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Collections.sort(trs,(daftarbuku, t1) -> {
            return daftarbuku.getTanggal().compareTo(t1.getTanggal());
        });
        return trs;
    }
    private static void saveAllDataBuku(Context ctx, List<Buku> trs) {
        List<JSONObject> jsonTrs = new ArrayList<JSONObject>();
        JSONArray jsonArr = new JSONArray();
        for (Buku tr : trs) {
            jsonArr.put(tr.toJSONObject());
        }
        getSharedPref(ctx).edit().putString(TRANS_KEY,jsonArr.toString()).apply();
    }
    public static void addDataBuku(Context ctx, Buku tr) {
        List<Buku> trs = getAllBuku(ctx);
        trs.add(tr);
        saveAllDataBuku(ctx,trs);
    }
    public static void editDataBuku(Context ctx, Buku tr) {
        List<Buku> trs = getAllBuku(ctx);
        for (Buku tre:trs) {
            trs.remove(tre);
            trs.add(tr);
        }
    }
    public static void hapusDataBuku(Context ctx, String id) {
        List<Buku> trs = getAllBuku(ctx);
        for (Buku tr:trs) {
            trs.remove(tr);
        }
    }
}
