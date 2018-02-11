package com.noriter.bobpul.Server;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.noriter.bobpul.Activity.BoardRead_bfActivity;
import com.noriter.bobpul.Activity.LocalBoardActivity;
import com.noriter.bobpul.Activity.MainActivity;
import com.noriter.bobpul.Activity.MeetingAfterActivity;
import com.noriter.bobpul.Activity.MeetingAfterSuccessActivity;
import com.noriter.bobpul.ListView.ListViewAdapter;
import com.noriter.bobpul.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by gugu on 2017-05-18.
 */

public class HttpAsyncTaskBob extends AsyncTask<String, Integer, String> {

    private LoadManager load;
    private String strTemp;
    private DO nowCheck;
    private Context context;
    public static String nickname;

    public HttpAsyncTaskBob(DO info, Context context) {
        this.nowCheck = info;
        this.context = context;
        load = new LoadManager(info);
        nickname=info.getNick();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        setStrTemp(load.request());
        System.out.println(strTemp);

        return strTemp;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        if (nowCheck.getAction().equals("login")) {
            Log.d("Login Test", s);
            if (!s.trim().equals("0")) {
                String nick = null, rank = null;
                try {
                    JSONObject nickInfo = new JSONObject(s);
                    JSONArray boardArray = nickInfo.getJSONArray("user_info");
                    JSONObject realBoardInfo = null;

                    realBoardInfo = boardArray.getJSONObject(0);

                    nick = realBoardInfo.getString("nickName");
                    rank = realBoardInfo.getString("userLevel");


                } catch (JSONException e) {

                }

                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("session_id", nowCheck.getId());
                intent.putExtra("state", "login");
                intent.putExtra("session_nick", nick);
                intent.putExtra("session_rank", rank);
                context.startActivity(intent);
                ((Activity) context).finish();
            } else {
                Toast.makeText(context, "회원정보가 일치하지 않습니다.", Toast.LENGTH_LONG).show();
            }
        } else if (nowCheck.getAction().equals("addOn")) {
            if (s.trim().equals("1")) {
                Intent intent = new Intent(context, LocalBoardActivity.class);
                context.startActivity(intent);
                ((Activity) context).finish();
            } else {
                Toast.makeText(context, "다시 입력해주세요.", Toast.LENGTH_LONG).show();
            }
        } else if (nowCheck.getAction().equals("search_a")) {

            ListView listView = (ListView) ((Activity) context).findViewById(R.id.lvLocalBoard);
            final ListViewAdapter adapter = new ListViewAdapter();

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    DO info = adapter.arrData.get(position);
                    Intent intent = new Intent(context, BoardRead_bfActivity.class);
                    intent.putExtra("title", info.getBoardTitle());
                    intent.putExtra("content", info.getBoardContent());
                    intent.putExtra("min", info.getBoardMin());
                    intent.putExtra("max", info.getBoardMax());
                    context.startActivity(intent);
                    ((Activity) context).finish();
                }
            });

            try {
                JSONObject boardInfo = new JSONObject(s);
                JSONArray boardArray = boardInfo.getJSONArray("board_Data");
                JSONObject realBoardInfo = null;

                for (int cnt = 0; cnt < boardArray.length(); cnt++) {
                    realBoardInfo = boardArray.getJSONObject(cnt);
                    adapter.addBoardInfo(realBoardInfo.getString("userNick"), realBoardInfo.getString("userLevel"), realBoardInfo.getString("title"), realBoardInfo.getString("openM"), realBoardInfo.getString("openD"));
                }
                listView.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (nowCheck.getAction().equals("search_m")) {
            ListView listView = (ListView) ((Activity) context).findViewById(R.id.lvPopularBoard);
            final ListViewAdapter adapter = new ListViewAdapter();

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    DO info = adapter.arrData.get(position);
                    Intent intent = new Intent(context, BoardRead_bfActivity.class);
                    intent.putExtra("title", info.getBoardTitle());
                    intent.putExtra("content", info.getBoardContent());
                    intent.putExtra("min", info.getBoardMin());
                    intent.putExtra("max", info.getBoardMax());
                    context.startActivity(intent);
                    ((Activity) context).finish();
                }
            });

            try {
                JSONObject boardInfo = new JSONObject(s);
                JSONArray boardArray = boardInfo.getJSONArray("board_Data");
                JSONObject realBoardInfo = null;

                for (int cnt = 0; cnt < boardArray.length(); cnt++) {
                    realBoardInfo = boardArray.getJSONObject(cnt);
                    adapter.addPopularBoardInfo(realBoardInfo.getString("userNick"), realBoardInfo.getString("userLevel"), realBoardInfo.getString("title"), realBoardInfo.getString("openM"), realBoardInfo.getString("openD"));
                }
                listView.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else if(nowCheck.getAction().equals("mypage")){
            ListView listView = (ListView) ((Activity) context).findViewById(R.id.lvMypage);
            final ListViewAdapter adapter = new ListViewAdapter();
            Log.d("받은 데이터",s.trim());

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    DO info = adapter.arrData.get(position);
                    Toast.makeText(context, info.getMeetingName(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context, MeetingAfterActivity.class);
                    intent.putExtra("session_nick", info.getNick());
                    context.startActivity(intent);
                    ((Activity) context).finish();
                }
            });

            try {
                Log.d("받은 데이터",s.trim());
                JSONObject mypageInfo = new JSONObject(s.trim());
                JSONArray mypageArray = mypageInfo.getJSONArray("meet");
                JSONObject realMypageInfo;

                for (int cnt = 0; cnt < mypageArray.length(); cnt++) {
                    realMypageInfo = mypageArray.getJSONObject(cnt);
                    Log.d("권한 값",realMypageInfo.getString("reviewpermission"));
                    adapter.addMyPageInfo(realMypageInfo.getString("meetname"), realMypageInfo.getString("reviewpermission"));
                }
                listView.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else if(nowCheck.getAction().equals("reviewdata")){
            Log.d("review Test", s);
            if(s.trim().equals("1")){
                Log.d("받은 데이터",s.trim());
                Intent confirm_open = new Intent(context, MeetingAfterSuccessActivity.class);
                confirm_open.putExtra("session_nick", nickname);
                context.startActivity(confirm_open);
                ((Activity)context).finish();
            }
            else{
                Toast.makeText(context, "리뷰 전송 실패, 다시 입력해주세요.", Toast.LENGTH_LONG).show();
            }
        }
    }



    public String getStrTemp() {
        return strTemp;
    }

    public void setStrTemp(String strTemp) {
        this.strTemp = strTemp;
    }


}
