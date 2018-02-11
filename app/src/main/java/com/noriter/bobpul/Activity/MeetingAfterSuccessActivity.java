package com.noriter.bobpul.Activity;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.noriter.bobpul.R;

public class MeetingAfterSuccessActivity extends AppCompatActivity implements View.OnClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_after_success);

        Button btnHome = (Button)findViewById(R.id.btnMeetingAfterSuccess);
        btnHome.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=getIntent();
        Intent home_open = new Intent(this, MyPageActivity.class);
        home_open.putExtra("session_nick", intent.getStringExtra("session_nick"));
        startActivity(home_open);
        finish();
    }
}
