package com.example.test_task.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.test_task.exception.TokenNotExist;

public class PreferencesSaver {

    private static final String AUTH_STORAGE = "auth_storage";
    private static final String TOKEN_AUTH = "token_auth";

    private final SharedPreferences preferences;

    public PreferencesSaver(Context context) {
        preferences = context.getSharedPreferences(AUTH_STORAGE, Context.MODE_PRIVATE);
    }

    public void saveToken(String token) {
        preferences.edit().putString(TOKEN_AUTH, token).apply();
    }

    public String readToken() throws TokenNotExist {
        if (isTokenExist()) return preferences.getString(TOKEN_AUTH, "");
        throw new TokenNotExist("Token doesn't exist");
    }

    public boolean isTokenExist() {
        return preferences.contains(TOKEN_AUTH);
    }

    public void removeToken() {
        preferences.edit().remove(TOKEN_AUTH).apply();
    }

}
