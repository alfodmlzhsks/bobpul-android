package com.noriter.bobpul.Activity;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.noriter.bobpul.R;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnAlertInfo, btnVerInfo, btnReport;
    Intent my_session = getIntent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        btnAlertInfo = (Button)findViewById(R.id.btnAlertInfo);
        btnVerInfo = (Button)findViewById(R.id.btnVerInfo);
        btnReport = (Button)findViewById(R.id.btnReport);

        btnAlertInfo.setOnClickListener(this);
        btnVerInfo.setOnClickListener(this);
        btnReport.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnAlertInfo:
                Intent open_AlertInfo = new Intent(this, AlertActivity.class);
                startActivity(open_AlertInfo);
                finish();
                break;
            case R.id.btnVerInfo:
                Intent open_verInfo = new Intent(this, VersionActivity.class);
                startActivity(open_verInfo);
                finish();
                break;
        }
    }
}
