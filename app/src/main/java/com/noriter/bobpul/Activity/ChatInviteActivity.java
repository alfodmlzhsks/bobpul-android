package com.noriter.bobpul.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.noriter.bobpul.ListView.ListViewAdapter;
import com.noriter.bobpul.R;

public class ChatInviteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_invite);

        ListView listView;
        ListViewAdapter adapter;

        adapter = new ListViewAdapter();

        adapter.addChatInvite("사진1", "gugu", "20");
        adapter.addChatInvite("사진2", "bidulgi", "23");
        adapter.addChatInvite("사진3", "tiger", "16");
        adapter.addChatInvite("사진1", "gugu", "20");
        adapter.addChatInvite("사진2", "bidulgi", "23");
        adapter.addChatInvite("사진3", "tiger", "16");
        adapter.addChatInvite("사진1", "gugu", "20");
        adapter.addChatInvite("사진2", "bidulgi", "23");
        adapter.addChatInvite("사진3", "tiger", "16");
        adapter.addChatInvite("사진1", "gugu", "20");
        adapter.addChatInvite("사진2", "bidulgi", "23");
        adapter.addChatInvite("사진3", "tiger", "16");
        adapter.addChatInvite("사진1", "gugu", "20");
        adapter.addChatInvite("사진2", "bidulgi", "23");
        adapter.addChatInvite("사진3", "tiger", "16");

        listView = (ListView)findViewById(R.id.lvChatInvite);
        listView.setAdapter(adapter);
    }
}
