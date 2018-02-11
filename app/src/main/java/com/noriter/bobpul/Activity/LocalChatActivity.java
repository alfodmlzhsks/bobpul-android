package com.noriter.bobpul.Activity;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.noriter.bobpul.ListView.ListViewAdapter;
import com.noriter.bobpul.R;

public class LocalChatActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_chat);

        Button btnCreateChat;
        ListView listView;
        ListViewAdapter adapter;

        Spinner sAge = (Spinner) findViewById(R.id.sLocalChatAge);
        Spinner sGender = (Spinner) findViewById(R.id.sLocalChatGender);

        //sAge와 sGender둘다 만족하는 조건으로 리스트뷰를 걸러서 보여줘야함

        adapter = new ListViewAdapter();

        adapter.addLocalChatInfo("비둘기잡아먹으러가요~", "10/14");
        adapter.addLocalChatInfo("닭잡아먹으러가요~", "05/04");
        adapter.addLocalChatInfo("비둘기잡아먹으러가요~", "10/14");
        adapter.addLocalChatInfo("닭잡아먹으러가요~", "05/04");
        adapter.addLocalChatInfo("비둘기잡아먹으러가요~", "10/14");
        adapter.addLocalChatInfo("닭잡아먹으러가요~", "05/04");
        adapter.addLocalChatInfo("비둘기잡아먹으러가요~", "10/14");
        adapter.addLocalChatInfo("닭잡아먹으러가요~", "05/04");
        adapter.addLocalChatInfo("비둘기잡아먹으러가요~", "10/14");
        adapter.addLocalChatInfo("닭잡아먹으러가요~", "05/04");
        adapter.addLocalChatInfo("비둘기잡아먹으러가요~", "10/14");
        adapter.addLocalChatInfo("닭잡아먹으러가요~", "05/04");
        adapter.addLocalChatInfo("비둘기잡아먹으러가요~", "10/14");
        adapter.addLocalChatInfo("닭잡아먹으러가요~", "05/04");

        listView = (ListView)findViewById(R.id.lvLocalChatList);
        listView.setAdapter(adapter);

        btnCreateChat = (Button)findViewById(R.id.btnLocalChatCreate);
        btnCreateChat.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnLocalChatCreate:
                Intent chatCreate_open = new Intent(this, LocalChatCreateActivity.class);
                startActivity(chatCreate_open);
                break;
            default:
                break;
        }
    }
}
