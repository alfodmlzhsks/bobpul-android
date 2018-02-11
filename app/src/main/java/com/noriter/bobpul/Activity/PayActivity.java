package com.noriter.bobpul.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.noriter.bobpul.R;

public class PayActivity extends AppCompatActivity {
    Button btnPay;
    ImageView imbT;
    int cnt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pay);
        btnPay = (Button) findViewById(R.id.btnPAY);
        imbT = (ImageView) findViewById(R.id.imageView2);
        final String strColor = "#000000";
        final String strColor2 = "#FFCD00";

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPay.setTextColor(Color.parseColor(strColor));
//                btnPay.setBackgroundColor(Color.parseColor(strColor2));
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intent);
                finish();

            }
        });
        imbT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cnt++;
                if (cnt % 2 != 0) {
                    imbT.setImageDrawable(getDrawable(R.drawable.pay3));
                }
            }
        });
    }
}
