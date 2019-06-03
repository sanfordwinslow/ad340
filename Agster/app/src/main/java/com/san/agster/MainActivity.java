package com.san.agster;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenu;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;


public class MainActivity extends AppCompatActivity {
    private Button button;

    private EditText myEmailField;
    private EditText myPasswordField;

    private static final String TAG = "Sign In";
    private static final String NAME = "Sanford Winslow - AD340 - Tuesday";
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FirebaseApp.initializeApp(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button button1 = (Button) findViewById(R.id.button_toast);

        myEmailField = (EditText) findViewById(R.id.editText3);
        myPasswordField = (EditText) findViewById(R.id.editText4);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        Button button2 = (Button) findViewById(R.id.button2_toast);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openButton2();
            }
        });

        Button button3 = (Button) findViewById(R.id.button3_toast);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openButton3();
            }
        });

        Button button4 = (Button) findViewById(R.id.button4_toast);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openButton4();
            }
        });
    }



    private void login() {
        Log.d(TAG, "login");
        if(!validateForm()) {
            return;
        }

        String email = myEmailField.getText().toString();
        String password = myPasswordField.getText().toString();

        FirebaseAuth myAuth = FirebaseAuth.getInstance();
        myAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                    Log.d("Firebase", "signIn:onComplete: " + task.isSuccessful());

                    if(task.isSuccessful()) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(NAME).build();

                        user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()) {
                                    Log.d("Firebase", "user profile updated");
                                    startActivity(new Intent(MainActivity.this, TeamActivity.class));
                                }
                            }
                        });
            } else {
                        Log.d("Firebase", "sign in failed");
                        Toast.makeText(MainActivity.this, "Sign In Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean validateForm() {
        boolean result = true;
        if(TextUtils.isEmpty(myEmailField.getText().toString())) {
            myEmailField.setError("Email Required");
            result = false;
        } else {
            myEmailField.setError(null);
        }

        if(TextUtils.isEmpty(myPasswordField.getText().toString())) {
            myPasswordField.setError("Password Required");
            result = false;
        } else {
            myPasswordField.setError(null);
        }
        return result;
    }

    public void openButton2() {
        Intent intent = new Intent(this, ZombieViewActivity.class);
        startActivity(intent);
    }

    public void openButton3() {
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }

    public void openButton4() {
        Intent intent = new Intent(this, CameraList.class);
        startActivity(intent);
    }

    public void showToast5(View view) {
        Toast toast = Toast.makeText(this, R.string.toast_message5, Toast.LENGTH_SHORT);
        toast.show();
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
        switch (item.getItemId()){
            case R.id.item_info:
                Intent intent = new Intent(this, About.class);
                startActivity(intent);


        }
                //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}



