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
import android.widget.TextView;
import android.widget.Toast;

import com.noriter.bobpul.Server.DO;
import com.noriter.bobpul.Server.HttpAsyncTaskBob;
import com.noriter.bobpul.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnRegIdCheck, btnRegLiveAreaSearch, btnRegPhoneId, btnRegSuccess;
    EditText edtRegIdInfo, edtRegPWInfo, edtRegPWInfoAgain, edtRegBirthInfo, edtRegPhoneNumInfo;
    TextView tvRegLiveAreaInfo;

    DO dataSave = new DO();

    HttpAsyncTaskBob task;
    //버튼은 btn대문자
    //에딧은 edt대문자
    //텍스트뷰는 tv대문자
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        btnRegIdCheck = (Button) findViewById(R.id.btnRegIdCheck);
        btnRegLiveAreaSearch = (Button) findViewById(R.id.btnRegLiveAreaSearch);
        btnRegPhoneId = (Button) findViewById(R.id.btnRegPhoneId);
        btnRegSuccess = (Button) findViewById(R.id.btnRegSuccess);

        edtRegIdInfo = (EditText) findViewById(R.id.edtRegIdInfo);
        edtRegPWInfo = (EditText) findViewById(R.id.edtRegPWInfo);
        edtRegPWInfoAgain = (EditText) findViewById(R.id.edtRegPWInfoAgain);
        edtRegBirthInfo = (EditText) findViewById(R.id.edtRegBirthInfo);
        edtRegPhoneNumInfo = (EditText) findViewById(R.id.edtRegPhoneNumInfo);

        //tvRegLiveAreaInfo = (TextView) findViewById(R.id.tvRegLiveAreaInfo);

        btnRegIdCheck.setOnClickListener(this);
        btnRegLiveAreaSearch.setOnClickListener(this);
        btnRegPhoneId.setOnClickListener(this);
        btnRegSuccess.setOnClickListener(this);
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
        switch (v.getId()) {
            case R.id.btnRegIdCheck:
                break;
            case R.id.btnRegLiveAreaSearch:
                break;
            case R.id.btnRegPhoneId:
                break;
            case R.id.btnRegSuccess:
                dataSave.setId(edtRegIdInfo.getText().toString());
                dataSave.setPw(edtRegPWInfo.getText().toString());
                dataSave.setBirth(edtRegBirthInfo.getText().toString());
                dataSave.setTel(edtRegPhoneNumInfo.getText().toString());
                dataSave.setAddr(tvRegLiveAreaInfo.getText().toString());
                dataSave.setAction("addMem");
                connectCheck();
                Intent main_open = new Intent(this, MainActivity.class);
                main_open.putExtra("session_id", edtRegIdInfo.getText().toString());
                main_open.putExtra("state", "reg");
                startActivity(main_open);
                finish();
                break;
        }
    }
}
/*
            Intent i = new Intent(this, LoadManager.class);
            i.putExtra("ID", edtRegIdInfo.getText().toString());
            i.putExtra("PW", edtRegPWInfo.getText().toString());
            i.putExtra("PWAgain", edtRegPWInfoAgain.getText().toString());
            i.putExtra("Birth", edtRegBirthInfo.getText().toString());
            i.putExtra("PhoneNum", edtRegPhoneNumInfo.getText().toString());
            startActivity(i);
 */