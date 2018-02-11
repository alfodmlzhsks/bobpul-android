package com.noriter.bobpul.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.noriter.bobpul.R;

/**
 * Created by NoRiTer on 2017-06-26.
 */

public class TopBarFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //개발자가 정의한 디자인 파일을 인플레이션 시킨 후 반환된 뷰를 현재 메서드의 반환값으로 지정
        View view = inflater.inflate(R.layout.activity_top_bar, container, false);
        return view;
}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
