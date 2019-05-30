package com.san.agster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class NoNetworkConnection extends AppCompatActivity {
    private static final String TAG = "No Connection";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.no_network);
        Log.i(TAG, "started onCreate");
    }
}
