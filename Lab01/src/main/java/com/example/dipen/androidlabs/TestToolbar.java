package com.example.dipen.androidlabs;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.support.design.widget.Snackbar;
import  android.support.v7.widget.Toolbar;

public class TestToolbar extends AppCompatActivity {

    private String notificationMessage;
    private EditText edtNewMsg;
    private View parentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        parentView = findViewById(android.R.id.content);
        notificationMessage = "Please Enter New Message To Display";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.toolbarmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()){
            case R.id.action_one:
                Log.i("Toolbar","Option 1 Selected");
                Snackbar.make(parentView, notificationMessage, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
            case R.id.action_two:
                Log.i("Toolbar","Option 2 Selected");
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.selectYourChoice);
                builder.setPositiveButton(R.string.ok, (DialogInterface dialog, int id) -> {
                       finish();
                })
                .setNegativeButton(R.string.cancel, (DialogInterface dialog, int id) -> {
                       dialog.cancel();
                })
                .show();
                break;
            case R.id.action_three:
                Log.i("Toolbar","Option 3 Selected");
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                LayoutInflater inflater = this.getLayoutInflater();
                View childView = inflater.inflate(R.layout.custom_dialog_layout,null);
                builder1.setView(childView)
                        .setPositiveButton(R.string.notifyMsg, (DialogInterface dialog, int id) -> {
                                edtNewMsg = childView.findViewById(R.id.edt_newMsg);
                                notificationMessage = edtNewMsg.getText().toString();
                        })
                        .show();
                break;
            case R.id.action_about:
                Toast.makeText(getApplicationContext(),"Version 1.0, Dipen Parikh",Toast.LENGTH_LONG).show();
                break;
            default:
                Log.i("Toolabar","Invalid Option selected");
                break;
        }
        return true;
    }
}
