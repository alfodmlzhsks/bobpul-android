package com.noriter.bobpul.Activity;

import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.noriter.bobpul.ListView.ListViewAdapter;
import com.noriter.bobpul.Server.DO;
import com.noriter.bobpul.Server.HttpAsyncTaskBob;
import com.noriter.bobpul.R;

public class PopularBoardActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnCreateBoard, btnCreateChat;
    HttpAsyncTaskBob task = null;
    DO dataSave = new DO();
    ListViewAdapter adapter;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_board);

        Intent session = getIntent();

        System.out.println(session.getStringExtra("session_nick"));
        btnCreateBoard = (Button) findViewById(R.id.btnPopularBoard);
        btnCreateChat = (Button)findViewById(R.id.btnPopularChat);

        btnCreateBoard.setOnClickListener(this);
        btnCreateChat.setOnClickListener(this);
        adapter = new ListViewAdapter();
        dataSave.setId(session.getStringExtra("session_id"));
        dataSave.setAction("search_m");
        connectCheck();

        ImageView iv = new ImageView(this);
        iv.setImageResource(R.drawable.ic_launcher);

//        //임시로한거임
//        adapter.addBoardInfo("광화문에서 만나요~", "2", "비둘기", "2017-03-14", "서울", iv);
//        adapter.addBoardInfo("시청근처이신분~", "1", "참새", "2017-03-14", "이천", iv);
//        adapter.addBoardInfo("백악관 부수기`", "6", "까마귀", "2017-03-14", "부산", iv);
//        adapter.addBoardInfo("광화문에서 만나요~", "2", "비둘기", "2017-03-14", "서울", iv);
//        adapter.addBoardInfo("시청근처이신분~", "1", "참새", "2017-03-14", "이천", iv);
//        adapter.addBoardInfo("백악관 부수기`", "6", "까마귀", "2017-03-14", "부산", iv);
//        adapter.addBoardInfo("광화문에서 만나요~", "2", "비둘기", "2017-03-14", "서울", iv);
//        adapter.addBoardInfo("시청근처이신분~", "1", "참새", "2017-03-14", "이천", iv);
//        adapter.addBoardInfo("백악관 부수기`", "6", "까마귀", "2017-03-14", "부산", iv);
//        adapter.addBoardInfo("광화문에서 만나요~", "2", "비둘기", "2017-03-14", "서울", iv);
//        adapter.addBoardInfo("시청근처이신분~", "1", "참새", "2017-03-14", "이천", iv);
//        adapter.addBoardInfo("백악관 부수기`", "6", "까마귀", "2017-03-14", "부산", iv);
//        adapter.addBoardInfo("광화문에서 만나요~", "2", "비둘기", "2017-03-14", "서울", iv);
//        adapter.addBoardInfo("시청근처이신분~", "1", "참새", "2017-03-14", "이천", iv);
//        adapter.addBoardInfo("백악관 부수기`", "6", "까마귀", "2017-03-14", "부산", iv);
//

    }

    public void connectCheck() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        if ((networkInfo != null) && networkInfo.isConnected()) {
            task = new HttpAsyncTaskBob(dataSave, this);
            task.execute("");
        } else {
            Toast.makeText(this, "인터넷연결상태를 확인하세요", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnPopularBoard:
                Intent session = getIntent();
                Intent board_write = new Intent(getApplicationContext(), WriteBoardActivity.class);
                board_write.putExtra("session_id", session.getStringExtra("session_id"));
                board_write.putExtra("session_nick", session.getStringExtra("session_nick"));
                board_write.putExtra("session_rank", session.getStringExtra("session_rank"));
                board_write.putExtra("state", "1");
                startActivity(board_write);
                finish();
                break;
            case R.id.btnPopularChat:
                Intent chat_open = new Intent(getApplicationContext(), LocalChatActivity.class);
                startActivity(chat_open);
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (task != null) {
            task.cancel(true);
            task = null;
        }

    }

}
