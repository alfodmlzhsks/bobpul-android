package com.noriter.bobpul.Activity;

import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.noriter.bobpul.Server.DO;
import com.noriter.bobpul.Server.HttpAsyncTaskBob;
import com.noriter.bobpul.R;

public class WriteBoardActivity2 extends AppCompatActivity implements View.OnClickListener {

    EditText edtResInfo, edtMeetingAlias, edtPriceInput;
    ImageButton imbBoardWriteCom, imbBoardWritePrev;
    String month, day, min, max;
    DO dataSave = new DO();
    HttpAsyncTaskBob task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_board2);

        edtResInfo = (EditText) findViewById(R.id.edtResInfo);
        edtMeetingAlias = (EditText) findViewById(R.id.edtMeetingAlias);
        edtPriceInput = (EditText) findViewById(R.id.edtPriceInput);

        imbBoardWriteCom = (ImageButton) findViewById(R.id.imbBoardWriteCom);
        imbBoardWritePrev = (ImageButton) findViewById(R.id.imbBoardWritePrev);

        final TextView tvMin = (TextView) findViewById(R.id.tvBoardMin);
        final TextView tvMax = (TextView) findViewById(R.id.tvBoardMax);

        Spinner sMin = (Spinner) findViewById(R.id.sBoardMinCount);
        Spinner sMax = (Spinner) findViewById(R.id.sBoardMaxCount);
        Spinner sDateMonth = (Spinner) findViewById(R.id.sDateMonth);
        Spinner sDateDay = (Spinner) findViewById(R.id.sDateDay);


        sMin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tvMin.setText((String) parent.getItemAtPosition(position));
                min = tvMin.getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sMax.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tvMax.setText((String) parent.getItemAtPosition(position));
                max = tvMax.getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sDateMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                month = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sDateDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                day = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        imbBoardWriteCom.setOnClickListener(this);
        imbBoardWritePrev.setOnClickListener(this);
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
//        Bundle dataTemp = getIntent().getExtras();
        switch (v.getId()) {
            case R.id.imbBoardWriteCom:
                Intent dataTemp = getIntent();
                dataSave.setId(dataTemp.getStringExtra("session_id"));
                dataSave.setBoardTitle(dataTemp.getStringExtra("title"));
                dataSave.setBoardContent(dataTemp.getStringExtra("center"));
                dataSave.setType(Integer.parseInt(dataTemp.getStringExtra("state")));
                dataSave.setBoardMin(min);
                dataSave.setBoardMax(max);
                dataSave.setBoardPrice(Integer.parseInt(edtPriceInput.getText().toString()));
                dataSave.setBoardAlias(edtMeetingAlias.getText().toString());
                dataSave.setBoardResInfo(edtResInfo.getText().toString());
                dataSave.setBoardMonth(month);
                dataSave.setBoardDate(day);
                dataSave.setNick(dataTemp.getStringExtra("session_nick"));
                dataSave.setRank(dataTemp.getStringExtra("session_rank"));
                dataSave.setAction("addOn");
                connectCheck();
                break;
            case R.id.imbBoardWritePrev:
                Intent prevTemp = getIntent();
                Intent prev_open = new Intent(this, WriteBoardActivity.class);
                prev_open.putExtra("title", prevTemp.getStringExtra("title"));
                prev_open.putExtra("center", prevTemp.getStringExtra("center"));
                startActivity(prev_open);
                finish();
        }


    }
}
