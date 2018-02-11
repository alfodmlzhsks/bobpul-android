package com.noriter.bobpul.ListView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.noriter.bobpul.Activity.MeetingAfterActivity;
import com.noriter.bobpul.LocalChatItem;
import com.noriter.bobpul.R;
import com.noriter.bobpul.Server.DO;
import com.noriter.bobpul.Server.HttpAsyncTaskBob;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by gugu on 2017-05-28.
 */

/**
 * 미완성   3 5  완성 1 2 4 6
 */


public class ListViewAdapter extends BaseAdapter {

    private ArrayList<LocalChatItem> arrLocalChatItem = new ArrayList<LocalChatItem>();
    public ArrayList<DO> arrData = new ArrayList<DO>();

    private static int token = 0; // 구분자

    @Override
    public int getCount() {
        return arrData.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();
        if (token == 1) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.listview_localboard, parent, false);
            }
            TextView tvTitle, tvRank, tvName, tvMonth;
            ImageView ivLogo;

            tvTitle = (TextView) convertView.findViewById(R.id.tvLocalBoardTitle);
            tvRank = (TextView) convertView.findViewById(R.id.tvLocalBoardRank);
            tvName = (TextView) convertView.findViewById(R.id.tvLocalBoardName);
            tvMonth = (TextView) convertView.findViewById(R.id.tvLocalBoardMonth);
//            ivLogo = (ImageView) convertView.findViewById(R.id.ivLocalBoardLogo);

            DO info = arrData.get(position);

            tvTitle.setText(info.getBoardTitle());
            tvRank.setText(info.getRank());
            tvName.setText(info.getNick());
            tvMonth.setText(info.getBoardMonth() + info.getBoardDate());

        } else if (token == 2) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.listview_localboard_bf, parent, false);
            }

            TextView tvNick, tvContext;
            tvNick = (TextView) convertView.findViewById(R.id.tvLocalBoardName_bf);
            tvContext = (TextView) convertView.findViewById(R.id.tvLocalBoardContext_bf);

            DO info = arrData.get(position);

            tvNick.setText(info.getNick());
            tvContext.setText(info.getCommentInfo());
        } else if (token == 3) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.listview_localchat, parent, false);
            }

            TextView tvTitle, tvCount;
            tvTitle = (TextView) convertView.findViewById(R.id.tvLocalChatTitle);
            tvCount = (TextView) convertView.findViewById(R.id.tvLocalChatCount);

            LocalChatItem item = arrLocalChatItem.get(position);

            tvTitle.setText(item.getTitle());
            tvCount.setText(item.getCount());
        } else if (token == 4) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.listview_chatinvite, parent, false);
            }

            TextView tvProfile, tvName, tvAge;

            tvProfile = (TextView) convertView.findViewById(R.id.tvChatInvite_profile);
            tvName = (TextView) convertView.findViewById(R.id.tvChatInvite_name);
            tvAge = (TextView) convertView.findViewById(R.id.tvChatInvite_age);

            DO info = arrData.get(position);

            tvProfile.setText(info.getProfile());
            tvName.setText(info.getNick());
            tvAge.setText(info.getAge());
        } else if (token == 5) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.listview_mypage, parent, false);
            }

            Button btnMymeetingName, btnMymeetingConfirm;
            TextView tvMymeetingAfter;

            btnMymeetingName = (Button) convertView.findViewById(R.id.btnMymeetingName);
            btnMymeetingConfirm = (Button) convertView.findViewById(R.id.btnMymeetingConfirm);

            tvMymeetingAfter = (TextView) convertView.findViewById(R.id.tvMymeetingAfter);

            final DO item = arrData.get(position);

            btnMymeetingName.setText(item.getMeetingName());
            btnMymeetingConfirm.setText(item.getMeetingName());

            tvMymeetingAfter.setText(item.getReviewPermission());

            btnMymeetingName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MeetingAfterActivity.class);
                    Log.d("모임명", item.getMeetingName());
                    intent.putExtra("meetname", item.getMeetingName());
                    intent.putExtra("session_nick", HttpAsyncTaskBob.nickname);
                    intent.putExtra("restaurantName",item.getMeetingName());
                    context.startActivity(intent);
                    ((Activity) context).finish();
                }
            });
        } else if (token == 6) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.listview_popularboard, parent, false);
            }

            TextView tvTitle, tvRank, tvName, tvDday, tvLocation;
            ImageView ivLogo;

            tvTitle = (TextView) convertView.findViewById(R.id.tvPopularBoardTitle);
            tvRank = (TextView) convertView.findViewById(R.id.tvPopularBoardRank);
            tvName = (TextView) convertView.findViewById(R.id.tvPopularBoardName);
            tvDday = (TextView) convertView.findViewById(R.id.tvPopularBoardDday);
            tvLocation = (TextView) convertView.findViewById(R.id.tvPopularBoardLocation);
            ivLogo = (ImageView) convertView.findViewById(R.id.ivPopularBoardLogo);

            DO info = arrData.get(position);

            tvTitle.setText(info.getBoardTitle());
            tvRank.setText(info.getRank());
            tvName.setText(info.getNick());
            tvDday.setText(info.getBoardMonth() + info.getBoardDate());
            tvLocation.setText(info.getStrLocation());

        }
        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return arrData.get(position);
    }

    public void addBoardInfo(String name, String rank, String title, String month, String date) {
        DO info = new DO();

        info.setBoardTitle(title);
        info.setRank(rank);
        info.setNick(name);
        info.setBoardDate(date + "일");
        info.setBoardMonth("2017년" + month + "월");
//        list.setIvLogo(ivLogo);
        token = 1;

        arrData.add(info);
    }

    public void addBoardInfo_bf(String id, String context) {
        DO info = new DO();

        info.setNick(id);
        info.setCommentInfo(context);
        token = 2;

        arrData.add(info);
    }

    public void addLocalChatInfo(String title, String count) {
        LocalChatItem list = new LocalChatItem();

        list.setTitle(title);
        list.setCount(count);
        token = 3;

        arrLocalChatItem.add(list);

    }

    public void addChatInvite(String profile, String nick, String age) {
        DO info = new DO();

        info.setProfile(profile);
        info.setNick(nick);
        info.setAge(age);
        token = 4;

        arrData.add(info);
    }

    public void addMyPageInfo(String meetingName, String reviewPermission) {
        DO list = new DO();

        list.setMeetingName(meetingName);
        list.setReviewPermission(reviewPermission);
        token = 5;

        arrData.add(list);
    }

    public void addPopularBoardInfo(String nick, String rank, String title, String month, String date) {
        DO info = new DO();

        info.setBoardTitle(title);
        info.setRank(rank);
        info.setNick(nick);
        info.setBoardDate(date + "일");
        info.setBoardMonth("2017년" + month + "월");
        //info.setStrLocation(location);
        //info.setIvLogo(ivLogo);
        token = 6;

        arrData.add(info);
    }
}
