package com.noriter.bobpul.Activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.noriter.bobpul.ListView.ListViewAdapter;
import com.noriter.bobpul.R;
import com.noriter.bobpul.Server.DO;
import com.noriter.bobpul.Server.HttpAsyncTaskBob;

public class MyPageActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnMeetDay, btnBalance, btnIdenUpdate;
    HttpAsyncTaskBob task = null;
    DO dataSave;
    ListViewAdapter adapter;

    @Override
    protected void onResume() {
        super.onResume();

        //list 초기화
        dataSave = null;
        adapter = null;
        dataSave = new DO();
        adapter = new ListViewAdapter();

        Intent session = getIntent();
        //dataSave.setId(session.getStringExtra("session_id"));
        dataSave.setNick(session.getStringExtra("session_nick"));
        dataSave.setAction("mypage");

        connectCheck();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);
        dataSave=new DO();
        Intent session = getIntent();
        //dataSave.setId(session.getStringExtra("session_id"));
        dataSave.setNick(session.getStringExtra("session_nick"));
        dataSave.setAction("mypage");

        btnMeetDay = (Button)findViewById(R.id.btnMeetingSch);
        btnBalance = (Button)findViewById(R.id.btnRestPriceInfo);
        btnIdenUpdate = (Button)findViewById(R.id.btnInfoUpdate);

        btnMeetDay.setOnClickListener(this);
        btnBalance.setOnClickListener(this);
        btnIdenUpdate.setOnClickListener(this);



        adapter = new ListViewAdapter();

        connectCheck();

        /*ListView listView;
        ListViewAdapter adapter;

        adapter = new ListViewAdapter();

        adapter.addMyPageInfo("통큰갈비", "X");

        listView = (ListView)findViewById(R.id.lvMyPageMeeting);
        listView.setAdapter(adapter);*/
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
            case R.id.btnMeetingSch:
                Intent meetingSch_open = new Intent(getApplicationContext(), MyPageActivity.class);
                startActivity(meetingSch_open);
                finish();
                break;
            case R.id.btnRestPriceInfo:
                Intent restPrice_open = new Intent(getApplicationContext(), BalanceActivity.class);
                startActivity(restPrice_open);
                break;
            case R.id.btnInfoUpdate:
                Intent session = getIntent();

                Intent infoUpdate_open = new Intent(getApplicationContext(), ChangePWActivity.class);
                infoUpdate_open.putExtra("session_id", session.getStringExtra("session_id"));
                startActivity(infoUpdate_open);
                finish();
                break;
            case R.id.btnMymeetingConfirm:
                Intent intent = new Intent(getApplicationContext(), MeetingAfterActivity.class);
                startActivity(intent);
                finish();
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
