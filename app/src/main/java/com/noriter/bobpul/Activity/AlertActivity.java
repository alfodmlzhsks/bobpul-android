package com.noriter.bobpul.Activity;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.noriter.bobpul.R;

public class AlertActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnBack, btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

        btnBack = (Button)findViewById(R.id.btnBack);
        btnHome = (Button)findViewById(R.id.btnHome);

        btnBack.setOnClickListener(this);
        btnHome.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnBack:
                Intent back = new Intent(this, SettingActivity.class);
                startActivity(back);
                finish();
                break;
            case R.id.btnHome:
                Intent home = new Intent(this, MainActivity.class);
                startActivity(home);
                finish();
                break;
        }
    }
}
