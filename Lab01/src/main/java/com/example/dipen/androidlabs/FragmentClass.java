package com.example.dipen.androidlabs;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by dipen on 4/1/2018.
 */

public class FragmentClass extends Fragment {
    Context parent;
    String message;
    String id;
    public void onCreate(Bundle b)
    {
        super.onCreate(b);
        Bundle getInfo = getArguments();
        message = getInfo.getString("Message");
        id = getInfo.getString("Id");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View gui = inflater.inflate(R.layout.fragment_layout, null);
        TextView tvMessage =(TextView) gui.findViewById(R.id.tv_Message);
        TextView tvId =(TextView) gui.findViewById(R.id.tv_Id);
        tvMessage.setText("Message: " + message);
        tvId.setText("Id: "+ id);
        return gui;
    }
}
