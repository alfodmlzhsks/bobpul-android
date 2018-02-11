package com.noriter.bobpul.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.noriter.bobpul.ListView.ListViewAdapter;
import com.noriter.bobpul.R;

public class BoardRead_bfActivity extends AppCompatActivity {
    private TextView tvboardTitleSpread, tvBoardCenterSpread, tvBoardMaxSpread, tvBoardNowSpread;
    private Button btnBoardCredit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_read_bf);
        tvboardTitleSpread = (TextView) findViewById(R.id.tvboardTitleSpread);
        tvBoardCenterSpread = (TextView) findViewById(R.id.tvBoardCenterSpread);
        tvBoardNowSpread = (TextView) findViewById(R.id.tvBoardNowSpread);
        tvBoardMaxSpread = (TextView) findViewById(R.id.tvBoardMaxSpread);
        btnBoardCredit = (Button) findViewById(R.id.btnBoardCredit);


        Intent intent = getIntent();
        final String Title = intent.getStringExtra("title");
        final String Content = intent.getStringExtra("content");
        final String min = intent.getStringExtra("min");
        final String max = intent.getStringExtra("max");

        Log.d("Test", Title + " " + Content + " " + min + " " + max);

        tvboardTitleSpread.setText(Title);
        //tvBoardCenterSpread.setText(Content);
        //tvBoardNowSpread.setText(min);
        //tvBoardMaxSpread.setText(max);

        btnBoardCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PayActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ListView listView;
        ListViewAdapter adapter;

        adapter = new ListViewAdapter();

        adapter.addBoardInfo_bf("강한새별", "저도 참여할래요");
        adapter.addBoardInfo_bf("김진영", "맛잇을거 같네요");


        listView = (ListView) findViewById(R.id.lvLocalBoard_bf);
        listView.setAdapter(adapter);
    }
}
