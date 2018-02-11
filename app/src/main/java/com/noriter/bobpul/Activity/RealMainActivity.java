package com.noriter.bobpul.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.noriter.bobpul.Server.DO;
import com.noriter.bobpul.Server.HttpAsyncTaskBob;
import com.noriter.bobpul.R;

public class RealMainActivity extends AppCompatActivity implements View.OnClickListener {

    private HttpAsyncTaskBob task;
    private DO info = new DO();
    private EditText edtLoginIDInfo, edtLoginPWInfo;
    private ImageButton imbLoginRegister, imbFindPW;
    private Button btnLoginSuccess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_main);

        imbLoginRegister = (ImageButton) findViewById(R.id.imbLoginRegister);
        btnLoginSuccess = (Button) findViewById(R.id.btnLoginSuccess);
        imbFindPW = (ImageButton) findViewById(R.id.imbFindPW);

        edtLoginIDInfo = (EditText) findViewById(R.id.edtLoginIDInfo);
        edtLoginPWInfo = (EditText) findViewById(R.id.edtLoginPWInfo);

        imbLoginRegister.setOnClickListener(this);
        btnLoginSuccess.setOnClickListener(this);
        imbFindPW.setOnClickListener(this);
    }

    public void connectCheck() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        if ((networkInfo != null) && networkInfo.isConnected()) {
            task = new HttpAsyncTaskBob(info, this);
            task.execute("");
        } else {
            Toast.makeText(this, "인터넷연결상태를 확인하세요", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imbLoginRegister: // 회원가입
                Intent reg_open = new Intent(this, RegisterActivity.class);
                startActivity(reg_open);
                finish();
                break;
            case R.id.btnLoginSuccess: // 로그인 전송 기능
                String strID = edtLoginIDInfo.getText().toString();
                String strPW = edtLoginPWInfo.getText().toString();
                if (strID.equals("") || strPW.equals("")) {
                    Toast.makeText(getApplicationContext(), "아이디와 비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    info.setId(strID);
                    info.setPw(strPW);
                    info.setAction("login");
                    connectCheck();
                }
                break;
            case R.id.imbFindPW:
                Intent findPW_open = new Intent(this, TempPWActivity.class);
                startActivity(findPW_open);
                finish();
            default:
                break;
        }
    }
}
