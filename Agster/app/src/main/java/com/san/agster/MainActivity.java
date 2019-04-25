package com.san.agster;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        button = (Button) findViewById(R.id.button2_toast);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openButton2();
            }
        });
    }

    public void openButton2() {
        Intent intent = new Intent(this, RecyclerViewActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showToast(View view) {
        Toast toast = Toast.makeText(this, R.string.toast_message1, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void showToast2(View view) {
        Toast toast = Toast.makeText(this, R.string.toast_message2, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void showToast3(View view) {
        Toast toast = Toast.makeText(this, R.string.toast_message3, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void showToast4(View view) {
        Toast toast = Toast.makeText(this, R.string.toast_message4, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void showToast5(View view) {
        Toast toast = Toast.makeText(this, R.string.toast_message5, Toast.LENGTH_SHORT);
        toast.show();
    }


}
