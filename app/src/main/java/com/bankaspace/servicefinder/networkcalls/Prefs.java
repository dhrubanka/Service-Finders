package com.bankaspace.servicefinder.networkcalls;


import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {

    public Prefs(Context context){this.context = context;}
    Context context;
    String PREFERENCE_FILE = "PREF_FILE";

    public static String ID = "ID";
    public static String EMAIL = "EMAIL";
    public static String USERNAME = "USERNAME";
    public static String PHONE = "PHONE";
    public static String PASSWORD = "PASSWORD";
    public static String PROFILE_IMAGE = "PROFILE_IMAGE";
    public static String GCM_NO = "GCM_NO";
    public static String STANDARD_FONT = "STANDARD_FONT";
    public static String FONT_SIZE = "FONT_SIZE";

/* --------------------------------------------------------------------------------------------------------- */
    public String getStringPreference(String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_FILE, Context.MODE_PRIVATE);
        String str = sharedPreferences.getString(key, "");
        return str;
    }

    public long getLongPreference(String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_FILE, Context.MODE_PRIVATE);
        long l = sharedPreferences.getLong(key, 0);
        return l;
    }


    public int getIntPreference(String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_FILE, Context.MODE_PRIVATE);
        int l = sharedPreferences.getInt(key, 0);
        return l;
    }

    public void setStringPreferenc(String key, String Value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =  sharedPreferences.edit();
        editor.putString(key, Value);
        editor.commit();
    }

    public void setLongPreferenc(String key, long Value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =  sharedPreferences.edit();
        editor.putLong(key, Value);
        editor.commit();
    }

    public void setIntPreferenc(String key, int Value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =  sharedPreferences.edit();
        editor.putInt(key, Value);
        editor.commit();
    }

}
