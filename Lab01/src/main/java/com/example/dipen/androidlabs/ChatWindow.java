package com.example.dipen.androidlabs;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatWindow extends Activity {

    private Button btnSend;
    private ListView lsChatView;
    private EditText edtChat;
    private ArrayList<String> lstMessages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        btnSend = findViewById(R.id.btn_send);
        lsChatView = findViewById(R.id.lv_ChatView);
        edtChat = findViewById(R.id.edt_Chat);
        lstMessages = new ArrayList<>();

        btnSend.setOnClickListener( view -> {
            String messageFromEdt = edtChat.getText().toString();
            lstMessages.add(messageFromEdt);
            edtChat.setText("");
        });

        ChatAdapter messageAdapter = new ChatAdapter(this);
        lsChatView.setAdapter(messageAdapter);

        messageAdapter.notifyDataSetChanged();

    }

    private class ChatAdapter extends ArrayAdapter<String> {

        public ChatAdapter(Context ctx){
            super(ctx,0);
        }

        @Override
        public int getCount() {
            return lstMessages.size();
        }

        @Override
        public String getItem(int position) {
            return lstMessages.get(position);
        }

        @Override
        public View getView(int position,  View convertView,  ViewGroup parent) {
            LayoutInflater li = ChatWindow.this.getLayoutInflater();
            View result = null;
            if((position%2) == 0)
                result = li.inflate(R.layout.chat_row_incoming,null);
            else
                result = li.inflate(R.layout.chat_row_outgoing,null);

            TextView message = result.findViewById(R.id.message_text);
            message.setText(getItem(position));
            return result;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
    }
}
