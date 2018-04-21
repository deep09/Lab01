package com.example.dipen.androidlabs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StartActivity extends Activity {

    protected final String ACTIVITY_NAME = "StartActivity";
    private Button btnImButton;
    private Button btnStartChat;
    private Button btnweatherForecast;
    private Button btnToolbarActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Log.i(ACTIVITY_NAME,"In onCreate()");
        btnImButton = findViewById(R.id.btn_ImButton);
        btnStartChat = findViewById(R.id.btn_startChat);
        btnweatherForecast = findViewById(R.id.btn_weatherForecast);
        btnToolbarActivity = findViewById(R.id.btnToolbar);

        btnImButton.setOnClickListener((view) -> {
            startActivityForResult(new Intent(StartActivity.this, ListItemsActivity.class),50);
        });

        btnStartChat.setOnClickListener( (view) -> {
            startActivity(new Intent(getApplicationContext(), ChatWindow.class));
            Log.i(ACTIVITY_NAME,"User Clicked Start Chat");
        });

        btnweatherForecast.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(),WeatherForecast.class));
        });

        btnToolbarActivity.setOnClickListener( view -> {
            startActivity(new Intent(getApplicationContext(),TestToolbar.class));
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 50)
        Log.i(ACTIVITY_NAME, "Returned to StartActivity.onActivityResult");
        else if(resultCode == Activity.RESULT_OK) {
            String response = data.getStringExtra("Response");
            Toast.makeText(this, "ListItemsActivity passed: "+response, Toast.LENGTH_LONG).show();
        }
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
}
