package com.example.dipen.androidlabs;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatWindow extends Activity {

    private Button btnSend;
    private ListView lsChatView;
    private EditText edtChat;
    private ArrayList<String> lstMessages;
    private ChatDatabaseHelper myHelper;
    private SQLiteDatabase myDB;
    private ContentValues cv;
    private Cursor myCursor;
    private SimpleCursorAdapter ca;
    private String[] msg = {"Hello", "How are you?"};
    private final static String ACTIVITY_NAME = "ChatWindow";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        btnSend = findViewById(R.id.btn_send);
        lsChatView = findViewById(R.id.lv_ChatView);
        edtChat = findViewById(R.id.edt_Chat);
        lstMessages = new ArrayList<>();
        cv = new ContentValues();

        myHelper = new ChatDatabaseHelper(this);
        myDB = myHelper.getWritableDatabase();

        btnSend.setOnClickListener( view -> {
            String messageFromEdt = edtChat.getText().toString();
            lstMessages.add(messageFromEdt);
            edtChat.setText("");
            cv.put(ChatDatabaseHelper.KEY_MESSAGE,messageFromEdt);
            myDB.insert(ChatDatabaseHelper.TABLE_NAME,"Null",cv);
            Log.i("Data Inserted",messageFromEdt+" has been added to the database");
        });

        ChatAdapter messageAdapter = new ChatAdapter(this);
        lsChatView.setAdapter(messageAdapter);
        messageAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onStart() {
        super.onStart();
        myCursor = myDB.rawQuery("SELECT * FROM "+ChatDatabaseHelper.TABLE_NAME+" ;",null);
        Log.i(ACTIVITY_NAME,"Cursor's column count: "+ myCursor.getColumnCount()+" Total Row Count: "+myCursor.getCount());
        myCursor.moveToFirst();
        for(int i=0;i<myCursor.getCount();i++){
            String msgFromDb = myCursor.getString(myCursor.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE));
            lstMessages.add(msgFromDb);
            Log.i(ACTIVITY_NAME, "SQL MESSAGE: "+ myCursor.getString(myCursor.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE)));
            myCursor.moveToNext();
        }
     }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myDB.close();

    }

    private class ChatAdapter extends ArrayAdapter<String> {

        public ChatAdapter(Context ctx){super(ctx,0);}

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
