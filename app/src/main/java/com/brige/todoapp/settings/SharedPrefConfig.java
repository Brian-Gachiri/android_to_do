package com.brige.todoapp.settings;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefConfig {

    private SharedPreferences sharedPreferences;
    private Context context;
    private static final String SHARED_PREF_NAME = "com.brige.todoapp.settings.SHARED_PREF";
    private static final String LOGIN_STATUS = "com.brige.todoapp.settings.login";
    private static final String USER_NAME = "com.brige.todoapp.settings.USER.NAME";
    private static final String USER_EMAIL = "com.brige.todoapp.settings.USER.EMAIL";
    private static final String USER_NUMBER = "com.brige.todoapp.settings.USER_NUMBER";
    private static final String USER_PASSWORD = "com.brige.todoapp.settings.USER_PASSWORD";

    public SharedPrefConfig(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }


    public  void setLoggingInStatus(boolean status){

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(LOGIN_STATUS, status);
        editor.apply();
    }

    public boolean isLoggedIn(){

        return sharedPreferences.getBoolean(LOGIN_STATUS, false);
    }

    public void setUserInfo(String name, String email, String number, String password){

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_NAME, name);
        editor.putString(USER_EMAIL, email);
        editor.putString(USER_NUMBER, number);
        editor.putString(USER_PASSWORD, password);
        editor.apply();


    }
}
