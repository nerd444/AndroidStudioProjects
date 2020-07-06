package com.nerd.youtube;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.nerd.youtube.adapter.RecyclerViewAdapter;
import com.nerd.youtube.model.Youtube;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editSearch;
    Button btnSearch;

    RequestQueue requestQueue;
    String youtubeUrl = "https://www.googleapis.com/youtube/v3/search?part=snippet&key=AIzaSyCJneNCSDht-dy8zvkAgR_D_A0Ue7m3mDU&maxResults=20&order=date&type=video&regionCode=KR";
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<Youtube> youtubeArrayList = new ArrayList<>();

    String nextPageToken;
    String pageToken = "";

    String searchUrl = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int lastPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                int totalCount = recyclerView.getAdapter().getItemCount();

                if(lastPosition+1 == totalCount){
                    //아이템 추가 ! 입맛에 맞게 설정하시면됩니다.
                    Toast.makeText(MainActivity.this, "Last Position", Toast.LENGTH_SHORT).show();

                    if (nextPageToken.compareTo(pageToken) != 0){
                        pageToken = nextPageToken;

                        String url = "";
                        if (searchUrl.isEmpty()){
                            url = youtubeUrl + "&pageToken" + pageToken;
                        }else{
                            url = searchUrl + "&pageToken" + pageToken;
                        }

                        // 이 url 로 네트워크 데이터 요청.
                        Log.i("AAA", "url");
                        addNetworkData(url);
                    }

                }
            }
        });

        editSearch = findViewById(R.id.editsearch);
        btnSearch = findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = editSearch.getText().toString().trim();
                searchUrl = "";
                if (key.isEmpty()){
                    searchUrl = youtubeUrl;
                }else{
                    searchUrl = youtubeUrl + "&q="+ key;
                }
                // 새로 바뀐 검색어로 데이터를 가져오기 위해서, 원래 들어있던 어레이리스트의
                // 데이터를 모드 지우고, 새로 받아온다.
                youtubeArrayList.clear();
                getNetworkData(searchUrl);
            }
        });

        // 발리 라이브러리 이용해서, 호출
        requestQueue = Volley.newRequestQueue(MainActivity.this);

        getNetworkData(youtubeUrl);

    }

    public void getNetworkData(String url){
        // 발리 라이브러리 이용해서, 호출
        // 로그 찍어본다.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            nextPageToken = response.getString("nextPageToken");
                            JSONArray items = response.getJSONArray("items");
                            for(int i = 0; i < items.length(); i++){
                                JSONObject jsonObject = items.getJSONObject(i);
                                // id -> videoId
                                JSONObject id = jsonObject.getJSONObject("id");
                                String videoId = id.getString("videoId");

                                Log.i("AAA", videoId);

                                // snippet -> {title, description, thumbnails}
                                JSONObject snippet = jsonObject.getJSONObject("snippet");
                                String title = snippet.getString("title");
                                String description = snippet.getString("description");
                                JSONObject thumbnails = snippet.getJSONObject("thumbnails");
                                // thumbnails -> {medium} -> url
                                JSONObject high = thumbnails.getJSONObject("high");
                                String url = high.getString("url");

                                Log.i("AAA", title+", "+description+", "+url);

                                Youtube youtube = new Youtube(title, description, videoId, url);
                                youtubeArrayList.add(youtube);

                            }

                            recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, youtubeArrayList);
                            recyclerView.setAdapter(recyclerViewAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(jsonObjectRequest);
    }

    public void addNetworkData(String url){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            nextPageToken = response.getString("nextPageToken");
                            JSONArray items = response.getJSONArray("items");
                            for(int i = 0; i < items.length(); i++){
                                JSONObject jsonObject = items.getJSONObject(i);
                                // id -> videoId
                                JSONObject id = jsonObject.getJSONObject("id");
                                String videoId = id.getString("videoId");

                                Log.i("AAA", videoId);

                                // snippet -> {title, description, thumbnails}
                                JSONObject snippet = jsonObject.getJSONObject("snippet");
                                String title = snippet.getString("title");
                                String description = snippet.getString("description");
                                JSONObject thumbnails = snippet.getJSONObject("thumbnails");
                                // thumbnails -> {medium} -> url
                                JSONObject high = thumbnails.getJSONObject("high");
                                String url = high.getString("url");

                                Log.i("AAA", title+", "+description+", "+url);

                                Youtube youtube = new Youtube(title, description, videoId, url);
                                youtubeArrayList.add(youtube);

                            }

                            recyclerViewAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(jsonObjectRequest);
    }
}
