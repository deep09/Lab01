package com.example.dipen.androidlabs;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.Button;

public class MessageDetails extends Activity {

    Button deleteBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_details);

        deleteBtn = findViewById(R.id.btn_deleteMsg);
        Bundle transferThisInfo = getIntent().getExtras();

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        FragmentClass df  =  new FragmentClass();
        df.setArguments( transferThisInfo );
        ft.addToBackStack("Any name, not used"); //only undo FT on back button
        ft.replace(  R.id.inside_FrameLayout , df);
        ft.commit();

    }
}
