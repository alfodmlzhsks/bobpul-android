package com.noriter.bobpul.Activity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.noriter.bobpul.R;
import com.noriter.bobpul.Server.DO;
import com.noriter.bobpul.Server.HttpAsyncTaskBob;

public class MeetingAfterActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnMeetingAfterConfirm;
    EditText edtBoardContext;
    TextView tvRestaurantName;
    DO dataSave = new DO();
    HttpAsyncTaskBob task = null;
    Intent session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_after);
        session=getIntent();
        btnMeetingAfterConfirm = (Button) findViewById(R.id.btnMeetingAfterConfirm);
        edtBoardContext=(EditText)findViewById(R.id.edtBoardContext);
        tvRestaurantName = (TextView)findViewById(R.id.tvRestaurantName);

        tvRestaurantName.setText(session.getStringExtra("restaurantName"));

        btnMeetingAfterConfirm.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        if(!edtBoardContext.getText().toString().equals("")){
            Log.d("리뷰",edtBoardContext.getText().toString());
            dataSave.setMeetingAfter(edtBoardContext.getText().toString());
            dataSave.setNick(session.getStringExtra("session_nick"));
            dataSave.setMeetingName(session.getStringExtra("meetname"));
            dataSave.setAction("reviewdata");
            connectCheck();
        }else {
            Toast.makeText(getApplicationContext(), "리뷰를 작성해주세요", Toast.LENGTH_LONG).show();
        }
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
}
