package com.nerd.lyricsfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText editArtist;
    EditText editSong;
    Button btnApi;
    TextView txtLyrics;

    String requestUrl = "https://api.lyrics.ovh/v1/";       //멤버변수에 넣어야한다. onCreate 안에 넣으려면 앞에 final 을 붙이는데, 그안에서만 사용가능.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editArtist = findViewById(R.id.editArtist);
        editSong = findViewById(R.id.editSong);
        btnApi = findViewById(R.id.btnApi);
        txtLyrics = findViewById(R.id.textLyrics);

        btnApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. 첫번째 에디트텍스트인, editArtist 에서, 가수 문자열을 가져온다.
                String artist = editArtist.getText().toString();
                // 2. 두번째 에디트텍스트인, editSong 에서, 노래 제목 문자열을 가져온다.
                String song = editSong.getText().toString();

                // 2-2. 여기서, 두 개 문자열에 글자가 들어있는지를 확인해야한다.(다른과정없이 여기서 문자열을 확인하는게 제일 깔끔함.)
                if (artist.isEmpty() || song.isEmpty()){
                    Toast.makeText(MainActivity.this, "가수와 노래제목을 정확히 입력하세요", Toast.LENGTH_SHORT).show();
                    txtLyrics.setText("");      //검색해서 노래가사가 나온뒤 둘 중 하나를 빼먹고 검색했을때 원래나왔던가사를 지워줌.
                    return;
                }

                // 3. 위의 두개 문자열을 파라미터 만든다.
                String url = requestUrl + artist + "/" + song;
                Log.i("song", "url : " + url);

                // 4. api 를 요청한다.

                // volley 라이브러리의 RequestQueue 클래스를 객체로 생성한다.
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() { //응답을 받아오는 http 프로토콜 방식 = GET, null = 우리가 리퀘스트할때 제이슨한테 보낼거에 없다고 씀.
                    @Override
                    public void onResponse(JSONObject response) {   // 첫번째 콜백함수
                        // 정상적으로, api 호출이 완료되었을때 호출됨.
                        // response 변수에, 데이터가 실려서 옵니다.
                        // 여기서, 우리가 원하는 데이터를 파싱합니다.
                        Log.i("song", "result : " + response.toString());
                        try {
                            txtLyrics.setText(response.getString("lyrics"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {    // 두번째 콜백함수
                        // 서버가 문제가 생겨서, 정상적으로 동작하지 않을때
                        // 이 메소드가 호출됩니다.
                        // error 변수에, 왜 에러가 났는지 데이터가 들어있음.
                    }
                });
            // 네트워크로 호출하는 코드.
            requestQueue.add(jsonObjectRequest);    //호출.
            }
        });

    }
}
