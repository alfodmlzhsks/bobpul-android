package com.noriter.bobpul.Activity;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.noriter.bobpul.R;

public class WriteBoardActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edtBoardTitle, edtBoardCenter;
    ImageButton imbBoardWriteNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_board);

        imbBoardWriteNext = (ImageButton)findViewById(R.id.imbBoardWriteNext);

        edtBoardTitle = (EditText)findViewById(R.id.edtBoardTitle);
        edtBoardCenter = (EditText)findViewById(R.id.edtBoardCenter);

        imbBoardWriteNext.setOnClickListener(this);

        Intent session = getIntent();
        edtBoardTitle.setText(session.getStringExtra("title"));
        edtBoardCenter.setText(session.getStringExtra("center"));
    }

    @Override
    public void onClick(View v) {

//        Bundle saveData = new Bundle();
//        saveData.putString("title", edtBoardTitle.getText().toString());
//        saveData.putString("center", edtBoardCenter.getText().toString());

        Intent session = getIntent();

        Intent write2_open = new Intent(this, WriteBoardActivity2.class);
        write2_open.putExtra("session_id", session.getStringExtra("session_id"));
        write2_open.putExtra("session_nick", session.getStringExtra("session_nick"));
        write2_open.putExtra("session_rank", session.getStringExtra("session_rank"));
        write2_open.putExtra("state", session.getStringExtra("state"));
        write2_open.putExtra("title", edtBoardTitle.getText().toString());
        write2_open.putExtra("center", edtBoardCenter.getText().toString());

//        write2_open.putExtras(saveData);
        startActivity(write2_open);
        finish();
    }
}
