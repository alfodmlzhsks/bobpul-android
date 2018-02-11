package com.noriter.bobpul.Activity;

import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.noriter.bobpul.Server.DO;
import com.noriter.bobpul.Server.HttpAsyncTaskBob;
import com.noriter.bobpul.R;

public class ChangePWActivity extends AppCompatActivity implements View.OnClickListener{

    DO dataSave = new DO();
    HttpAsyncTaskBob task;
    EditText edtNewPW, edtNewPWAgain, edtNewNick;
    Button btnChangeSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pw);

        btnChangeSave = (Button)findViewById(R.id.btnSavePW);

        edtNewPW = (EditText)findViewById(R.id.edtNewPW);
        edtNewPWAgain = (EditText)findViewById(R.id.edtNewPWAgain);
        edtNewNick = (EditText)findViewById(R.id.edtNewNick);

        btnChangeSave.setOnClickListener(this);
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

        Intent session = getIntent();
        String pw, pwAgain;

        pw=edtNewPW.getText().toString();
        pwAgain=edtNewPWAgain.getText().toString();

        if(pw.equals(pwAgain)) {
            dataSave.setId(session.getStringExtra("session_id"));
            dataSave.setChgPw(edtNewPW.getText().toString());
            dataSave.setChgPwAgain(edtNewPWAgain.getText().toString());
            dataSave.setNick(edtNewNick.getText().toString());
            dataSave.setAction("change_pw");
            connectCheck();

            Intent changeSave = new Intent(this, MyPageActivity.class);
            startActivity(changeSave);
            finish();
        }
        else {
            Toast.makeText(this, "비밀번호가 일치하지 않습니다!", Toast.LENGTH_LONG).show();
            return;
        }




    }

}
