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
        setContentView(R.layout.fragment_layout);//the screen will be pink

        deleteBtn = findViewById(R.id.btn_deleteMsg);
        Bundle transferThisInfo = getIntent().getExtras();

        //tablet click handler code:
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        FragmentClass df  =  new FragmentClass();
        df.setArguments( transferThisInfo );
        ft.addToBackStack("Any name, not used"); //only undo FT on back button
        ft.replace(  R.id.fr_layout , df);
        ft.commit();

        deleteBtn.setOnClickListener((view) -> {
            setResult(101,getIntent().putExtra("DeleteThisID",transferThisInfo.getInt("Id")));
            finish();
        });
    }
}
