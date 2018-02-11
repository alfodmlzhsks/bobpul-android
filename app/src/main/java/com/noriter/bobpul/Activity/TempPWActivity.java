package com.noriter.bobpul.Activity;

import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.noriter.bobpul.Server.DO;
import com.noriter.bobpul.Server.HttpAsyncTaskBob;
import com.noriter.bobpul.R;

public class TempPWActivity extends AppCompatActivity implements View.OnClickListener{

    HttpAsyncTaskBob task = null;
    DO dataSave = new DO();
    EditText edtTempPWEmail, edtTempID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_pw);

        ImageButton imbTempPWSuccess = (ImageButton)findViewById(R.id.imbTempPWSuccess);

        edtTempPWEmail = (EditText) findViewById(R.id.edtTempPWEmail);
        edtTempID = (EditText)findViewById(R.id.edtTempID);
        imbTempPWSuccess.setOnClickListener(this);
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
            case R.id.imbTempPWSuccess:
                dataSave.setTempPW(edtTempPWEmail.getText().toString());
                dataSave.setId(edtTempID.getText().toString());
                dataSave.setAction("crt_pw");

                connectCheck();

                Toast.makeText(this, "임시 비밀번호 발송 완료!", Toast.LENGTH_LONG).show();
                Intent login_open = new Intent(this, RealMainActivity.class);
                startActivity(login_open);
                finish();
                break;
            default:
                break;
        }
    }
}
