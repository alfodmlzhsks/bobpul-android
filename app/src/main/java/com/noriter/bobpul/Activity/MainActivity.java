package com.noriter.bobpul.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.noriter.bobpul.R;
import com.noriter.bobpul.Server.DO;
import com.noriter.bobpul.Server.HttpAsyncTaskBob;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton imbMyPage, imbSetting, imbLocalMenu, imbStarRes;
    HttpAsyncTaskBob task = null;
    DO dataSave = new DO();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent state = getIntent();
        final String id = state.getStringExtra("session_id");


            if (state.getStringExtra("state").equals("reg")) {
                final EditText edtNick = new EditText(this);

                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("입력");
                dialog.setView(edtNick);

                dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String nick = edtNick.getText().toString();
                        Toast.makeText(MainActivity.this, nick, Toast.LENGTH_SHORT).show();

                        dataSave.setNick(nick);
                        dataSave.setRank("1");
                        dataSave.setAction("addNick");
                        dataSave.setId(id);
                        connectCheck();
                    }
                });

                dialog.setNegativeButton("다음에 설정", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dataSave.setNick("null");
                        dataSave.setRank("1");
                        dataSave.setAction("addNick");
                        dataSave.setId(id);
                        dialog.cancel();
                    }
                });
                dialog.show();
            } else {
                dataSave.setNick(state.getStringExtra("session_nick"));
                dataSave.setRank(state.getStringExtra("session_rank"));
            }



        imbMyPage = (ImageButton) findViewById(R.id.imbMypage);
        imbSetting = (ImageButton) findViewById(R.id.imbSetting);
        imbLocalMenu = (ImageButton) findViewById(R.id.imbLocalMenu);
        imbStarRes = (ImageButton) findViewById(R.id.imbStarRes);

        imbMyPage.setOnClickListener(this);
        imbSetting.setOnClickListener(this);
        imbLocalMenu.setOnClickListener(this);
        imbStarRes.setOnClickListener(this);


    }

    public void connectCheck() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
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
        switch (v.getId()) {
            case R.id.imbMypage:
                //로그인정보 계속유지하기위한거
                Intent my_session = getIntent();
                Intent open_myPage = new Intent(getApplicationContext(), MyPageActivity.class);
                open_myPage.putExtra("session_id", my_session.getStringExtra("session_id"));
                open_myPage.putExtra("session_nick", dataSave.getNick());
                startActivity(open_myPage);
                break;
            case R.id.imbSetting:
                Intent set_session = getIntent();
                Intent open_setting = new Intent(getApplicationContext(), SettingActivity.class);
                open_setting.putExtra("session_id", set_session.getStringExtra("session_id"));
                startActivity(open_setting);
                break;
            case R.id.imbLocalMenu:
                Intent local_session = getIntent();
                Intent open_localMenu = new Intent(getApplicationContext(), LocalBoardActivity.class);
                open_localMenu.putExtra("session_id", local_session.getStringExtra("session_id"));
                open_localMenu.putExtra("session_nick", dataSave.getNick());
                open_localMenu.putExtra("session_rank", dataSave.getRank());
                startActivity(open_localMenu);
                break;
            case R.id.imbStarRes:
                Intent star_session = getIntent();
                Intent open_popular = new Intent(getApplicationContext(), PopularBoardActivity.class);
                open_popular.putExtra("session_id", star_session.getStringExtra("session_id"));
                open_popular.putExtra("session_nick", dataSave.getNick());
                open_popular.putExtra("session_rank", dataSave.getRank());
                startActivity(open_popular);
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
