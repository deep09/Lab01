package com.example.dipen.androidlabs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.content.SharedPreferences.*;

public class LoginActivity extends Activity {

    protected final String ACTIVITY_NAME = "LoginActivity";
    private  Button btnLogin;
    private EditText edtEmail;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.i(ACTIVITY_NAME,"In onCreate()");
        btnLogin =  findViewById(R.id.btnLogin);
        edtEmail = findViewById(R.id.edtEmail);
        sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("UserEmail","email@domain.com");
        edtEmail.setText(email);
        btnLogin.setOnClickListener( (view) -> {
                setEmailToSharedPreferences();
                startActivity(new Intent(LoginActivity.this,StartActivity.class));

        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(ACTIVITY_NAME,"In onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(ACTIVITY_NAME,"In onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(ACTIVITY_NAME,"In onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(ACTIVITY_NAME,"In onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME,"In onDestroy()");
    }

    public String getEmail(){
        return edtEmail.getText().toString();
    }

    public void setEmailToSharedPreferences(){
        Editor editor = sharedPreferences.edit();
        editor.putString("UserEmail",getEmail());
        editor.commit();
    }


}
