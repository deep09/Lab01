package com.example.dipen.androidlabs;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by dipen on 4/1/2018.
 */

public class FragmentClass extends Fragment {

    String message;
    int id;
    Bundle getInfo;
    public void onCreate(Bundle b)
    {
        super.onCreate(b);
        getInfo = getArguments();
        message = getInfo.getString("Message");
        id = getInfo.getInt("Id");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View gui = inflater.inflate(R.layout.fragment_layout, null);
        TextView tvMessage = gui.findViewById(R.id.tv_Message);
        TextView tvId = gui.findViewById(R.id.tv_Id);
        tvMessage.setText("Message: " + message);
        tvId.setText("Id: "+ id);
        Button btnDeleteMsg = gui.findViewById(R.id.btn_deleteMsg);
        btnDeleteMsg.setOnClickListener((View v) -> {
                getActivity().setResult(101,getActivity().getIntent().putExtra("DeleteThisID",getInfo.getInt("Id")));
                getActivity().finish();

        });
        return gui;
    }
}
