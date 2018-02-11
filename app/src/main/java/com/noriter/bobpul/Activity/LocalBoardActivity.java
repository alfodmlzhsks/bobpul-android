package com.noriter.bobpul.Activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.noriter.bobpul.R;
import com.noriter.bobpul.Server.DO;
import com.noriter.bobpul.Server.HttpAsyncTaskBob;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LocalBoardActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnCreateBoard, btnCreateChat;
    private ImageButton imbGetGPS;
    private HttpAsyncTaskBob task = null;
    private DO info = new DO();
    private ImageView iv;
    private TextView tvGps;
    private LocationManager lm;
    private boolean isGps = false;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_board);

        Intent session = getIntent();

        System.out.println(session.getStringExtra("session_nick"));

        btnCreateBoard = (Button) findViewById(R.id.btnLocalBoard);
        btnCreateChat = (Button) findViewById(R.id.btnLocalChat);
        imbGetGPS = (ImageButton) findViewById(R.id.imbGetGPS);
        tvGps = (TextView) findViewById(R.id.tvGPS);

        btnCreateBoard.setOnClickListener(this);
        btnCreateChat.setOnClickListener(this);
        imbGetGPS.setOnClickListener(this);

        info.setId(session.getStringExtra("session_id"));
        info.setAction("search_a");
        connectCheck();

//        ListView listView;
//        ListViewAdapter adapter;
//
//
//        ImageView iv = new ImageView(this);
//        iv.setImageResource(R.drawable.ic_launcher);
//        adapter = new ListViewAdapter();


        // while써서 닉네임 글 이거 null일때까지 db조사해서 뿌려주기
        //임시로한거임
        //제목, 등급, 닉네임, 년월일, 지역, 이미지
//        adapter.addBoardInfo("광화문에서 만나요~", "2", "비둘기", "2017-03-14", "서울", iv);
//        adapter.addBoardInfo("시청근처이신분~", "1", "참새", "2017-03-14", "이천", iv);
//        adapter.addBoardInfo("백악관 부수기`", "6", "까마귀", "2017-03-14", "부산", iv);
//        adapter.addBoardInfo("광화문에서 만나요~", "2", "비둘기", "2017-03-14", "서울", iv);
//        adapter.addBoardInfo("시청근처이신분~", "1", "참새", "2017-03-14", "이천", iv);
//        adapter.addBoardInfo("백악관 부수기`", "6", "까마귀", "2017-03-14", "부산", iv);
//        adapter.addBoardInfo("광화문에서 만나요~", "2", "비둘기", "2017-03-14", "서울", iv);
//        adapter.addBoardInfo("시청근처이신분~", "1", "참새", "2017-03-14", "이천", iv);
//        adapter.addBoardInfo("백악관 부수기`", "6", "까마귀", "2017-03-14", "부산", iv);
//        adapter.addBoardInfo("광화문에서 만나요~", "2", "비둘기", "2017-03-14", "서울", iv);
//        adapter.addBoardInfo("시청근처이신분~", "1", "참새", "2017-03-14", "이천", iv);
//        adapter.addBoardInfo("백악관 부수기`", "6", "까마귀", "2017-03-14", "부산", iv);
//        adapter.addBoardInfo("광화문에서 만나요~", "2", "비둘기", "2017-03-14", "서울", iv);
//        adapter.addBoardInfo("시청근처이신분~", "1", "참새", "2017-03-14", "이천", iv);
//        adapter.addBoardInfo("백악관 부수기`", "6", "까마귀", "2017-03-14", "부산", iv);

//
//        listView = (ListView)findViewById(R.id.lvLocalBoard);
//        listView.setAdapter(adapter);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isGps) {
            lm.removeUpdates(mLocationListener);
            isGps = false;
        }
    }

    public void connectCheck() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        if ((networkInfo != null) && networkInfo.isConnected()) {
            task = new HttpAsyncTaskBob(info, this);
            task.execute("");
        } else {
            Toast.makeText(this, "인터넷연결상태를 확인하세요", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLocalBoard: // 게시글 작성
                Intent session = getIntent();
                Intent board_write = new Intent(this, WriteBoardActivity.class);
                board_write.putExtra("session_id", session.getStringExtra("session_id"));
                board_write.putExtra("session_nick", session.getStringExtra("session_nick"));
                board_write.putExtra("session_rank", session.getStringExtra("session_rank"));
                board_write.putExtra("state", "0");
                startActivity(board_write);
                finish();
                break;
            case R.id.btnLocalChat: // 채팅 목록
                Intent chat_open = new Intent(this, LocalChatActivity.class);
                startActivity(chat_open);
                finish();
                break;
            case R.id.imbGetGPS:
                getPermission();
                // LocationManager 객체를 얻어온다
                lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                checkGpsService();
                // GPS 제공자의 정보가 바뀌면 콜백하도록 리스너 등록하기~!!!
                try {
                    lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, // 등록할 위치제공자
                            100, // 통지사이의 최소 시간간격 (miliSecond)
                            1, // 통지사이의 최소 변경거리 (m)
                            mLocationListener);
                    lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, // 등록할 위치제공자
                            100, // 통지사이의 최소 시간간격 (miliSecond)
                            1, // 통지사이의 최소 변경거리 (m)
                            mLocationListener);
                    isGps = true;
                } catch (SecurityException ex) {
                    ex.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    private void getPermission() {
        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(getApplicationContext(), "권한 승인", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                Toast.makeText(getApplicationContext(), "권한 거부" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };
        new TedPermission(this)
                .setPermissionListener(permissionListener)
                .setDeniedMessage("If you reject permission,you can not use this service\\n\\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                .check();
    }

    /**
     * 위도,경도로 주소구하기
     *
     * @param lat
     * @param lng
     * @return 주소
     */
    public static String getAddress(Context mContext, double lat, double lng) {
        String nowAddress = "현재 위치를 확인 할 수 없습니다.";
        Geocoder geocoder = new Geocoder(mContext, Locale.KOREA);
        List<Address> address;

        try {
            if (geocoder != null) {
                //세번째 파라미터는 좌표에 대해 주소를 리턴 받는 갯수로
                //한좌표에 대해 두개이상의 이름이 존재할수있기에 주소배열을 리턴받기 위해 최대갯수 설정
                address = geocoder.getFromLocation(lat, lng, 1);

                if (address != null && address.size() > 0) {
                    // 주소 받아오기
                    String currentLocationAddress = address.get(0).getAddressLine(0).toString();
                    nowAddress = currentLocationAddress;

                }
            }

        } catch (IOException e) {
            //Toast.makeText(, "주소를 가져 올 수 없습니다.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        return nowAddress;
    }

    //GPS 설정 체크
    private boolean checkGpsService() {
        String gps = android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

        if (!(gps.matches(".*gps.*") && gps.matches(".*network.*"))) {

            // GPS OFF 일때 Dialog 표시
            AlertDialog.Builder gsDialog = new AlertDialog.Builder(this);
            gsDialog.setTitle("위치 서비스 설정");
            gsDialog.setMessage("무선 네트워크 사용, GPS 위성 사용을 모두 체크하셔야 정확한 위치 서비스가 가능합니다.\n위치 서비스 기능을 설정하시겠습니까?");
            gsDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // GPS설정 화면으로 이동
                    Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    startActivity(intent);
                }
            })
                    .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    }).create().show();
            return false;

        } else {
            return true;
        }
    }

    private final LocationListener mLocationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            //여기서 위치값이 갱신되면 이벤트가 발생한다.
            //값은 Location 형태로 리턴되며 좌표 출력 방법은 다음과 같다.

            Log.d("test", "onLocationChanged, location:" + location);
            double longitude = location.getLongitude(); //경도
            double latitude = location.getLatitude();   //위도
            double altitude = location.getAltitude();   //고도
            float accuracy = location.getAccuracy();    //정확도
            String provider = location.getProvider();   //위치제공자
            String position = getAddress(getApplicationContext(), latitude, longitude);
            //Gps 위치제공자에 의한 위치변화. 오차범위가 좁다.
            //Network 위치제공자에 의한 위치변화
            //Network 위치는 Gps에 비해 정확도가 많이 떨어진다.
            tvGps.setText("현재위치 : " + position);
            tvGps.setTextSize(20);
        }

        public void onProviderDisabled(String provider) {
            // Disabled시
            Log.d("test", "onProviderDisabled, provider:" + provider);
        }

        public void onProviderEnabled(String provider) {
            // Enabled시
            Log.d("test", "onProviderEnabled, provider:" + provider);
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
            // 변경시
            Log.d("test", "onStatusChanged, provider:" + provider + ", status:" + status + " ,Bundle:" + extras);
        }
    };


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "LocalBoardActivity Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.noriter.bobpul/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "LocalBoardActivity Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.noriter.bobpul/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
