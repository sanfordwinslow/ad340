package com.san.agster;

import android.content.SharedPreferences;

import java.security.Key;

public class mockedSharedPreferences {

    private final SharedPreferences mockSharedPreferences;

    private final String KEY = "input";

    public mockedSharedPreferences(SharedPreferences sharedPreferences) {
        mockSharedPreferences = sharedPreferences;
    }

    public boolean saveEntry(String entry) {
        SharedPreferences.Editor mockEditor = mockSharedPreferences.edit();
        mockEditor.putString(KEY, entry);
        return mockEditor.commit();
    }

    public String getEntry() {
        return mockSharedPreferences.getString(KEY, "");
    }
}
