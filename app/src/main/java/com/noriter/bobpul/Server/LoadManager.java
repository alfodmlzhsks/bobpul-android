package com.noriter.bobpul.Server;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by gugu on 2017-05-18.
 */

public class LoadManager {
    private URL url;
    private HttpURLConnection conn;
    private BufferedReader buffer = null;
    private OutputStream os = null;

    public LoadManager(DO info) {
        try {
            if (info.getAction().equals("addMem")) {
                String m_url = "http://noring.iptime.org:7012/bapul/Login.jsp";
                String strRegsimpleAddr = info.getRegSimpleAddr();
                url = new URL(m_url + strRegsimpleAddr);
            } else if (info.getAction().equals("login")) {
                String m_url = "http://noring.iptime.org:7012/bapul/Login.jsp";
                String strLoginSimpleAddr = info.getLoginSimpleAddr();
                url = new URL(m_url + strLoginSimpleAddr);
            } else if (info.getAction().equals("change_pw")) {
                String m_url = "http://noring.iptime.org:7012/bapul/Mypage_Controller.jsp";
                String strNewPwSimpleAddr = info.getNewPWSimpleAddr();
                url = new URL(m_url + strNewPwSimpleAddr);
            } else if (info.getAction().equals("addOn")) {
                String m_url = "http://noring.iptime.org:7012/bapul/Writing_Control.jsp";
                String strLocalBoardAddr = info.getLocalBoardAddr();
                Log.d("tssad", strLocalBoardAddr);
                url = new URL(m_url + strLocalBoardAddr);
            } else if (info.getAction().equals("crt_pw")) {
                String m_url = "http://noring.iptime.org:7012/bapul/Mypage_Controller.jsp";
                String strTempAddr = info.getTempPWAddr();
                url = new URL(m_url + strTempAddr);
            } else if (info.getAction().equals("addNick")) {
                String m_url = "http://noring.iptime.org:7012/bapul/Mypage_Controller.jsp";
                String strNickAddr = info.getNickAddr();
                url = new URL(m_url + strNickAddr);
            } else if (info.getAction().equals("search_m")) {
                String m_url = "http://noring.iptime.org:7012/bapul/Writing_Control.jsp";
                String strBoardSearchAddr = info.getBoardSearchAddr();
                url = new URL(m_url + strBoardSearchAddr);
            } else if (info.getAction().equals("search_a")) {
                String m_url = "http://noring.iptime.org:7012/bapul/Writing_Control.jsp";
                String strBoardSearchAddr = info.getBoardSearchAddr();
                url = new URL(m_url + strBoardSearchAddr);
            }
            else if(info.getAction().equals("mypage")){
                String m_url = "http://noring.iptime.org:7012/bapul/Mypage_Controller.jsp";
                String strMyPageAddr = info.getMypageAddr();
                url = new URL(m_url + strMyPageAddr);
            }
            else if(info.getAction().equals("reviewdata")){
                String m_url = "http://noring.iptime.org:7012/bapul/Mypage_Controller.jsp";
                String strReviewAddr = info.getReviewAddr();
                url = new URL(m_url + strReviewAddr);
                Log.d("보내는 데이터",strReviewAddr);
            }
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String request() {
        String data = "";
        try {
            conn.connect();

            InputStream is = conn.getInputStream();
            buffer = new BufferedReader(new InputStreamReader(is));

            String line = null;

            while ((line = buffer.readLine()) != null) {
                data += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (buffer != null) {
                try {
                    buffer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }
}
