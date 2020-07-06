package com.nerd.photos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.nerd.photos.adapter.RecyclerViewAdapter;
import com.nerd.photos.model.Photos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    public static final String URL = "https://jsonplaceholder.typicode.com/photos";
    ArrayList<Photos> photosArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i("AAA","array 의 값은 : "+response.toString());

                        for (int i = 0; i < response.length(); i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                // userId, id, title, completed 를 가져온다.
                                int albumId = jsonObject.getInt("albumId");
                                int id = jsonObject.getInt("id");
                                String title = jsonObject.getString("title");
                                String url = jsonObject.getString("url");
                                String thumbnailUrl = jsonObject.getString("thumbnailUrl");
                                Log.i("AAA", "루프 "+i +"번쟤 userId"+albumId+ " ,id : "+id+", title : "+title+", url : "+url + ", ThumbnailUrl : "+thumbnailUrl);
                                // 위의 4개 다 가져왔으면, 클래스 하나 만들어서, 객체안에 저장
                                Photos photos = new Photos(albumId, id, title, url, thumbnailUrl);
                                // 어레이리스트 하나 만들어서, 이 어레이리스트에 100개 저장.
                                photosArrayList.add(photos);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this,photosArrayList);
                        recyclerView.setAdapter(recyclerViewAdapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // 이미지를 네트워크 통해서 다운로드 하여, 화면에 표시할 쓰레드.
    // 쓰레드란? 동시에 여러가지 작업을 가능하게 해주는 것.
    // 이러한 쓰레드를, 사용하기 편하게, 안드로이드에서 제공해 주는 클래스가,
    // AsyncTask 어씽크 태스크 (AsyncTask = 내부적으로 Volley 가 라이브러리에 이미 들어있음.)
    // AsyncTask<String, Void, Bitmap> <> 이 안은 프로그램마다 다 다르다.
    // String 부분은 doInBackground 파라메터부분, Bitmap 은 doInBackground 리턴부분(onPostExecute), Void 는 onProgressUpdate 부분 (update 함수가 없어서 꼭 써야함)
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        // 4. 멤버변수 설정
        Context context;

        // 5. 생성자
        public DownloadImageTask(Context context) {
            this.context = context;
        }

        // 2. 이 메소드 만들어준다.
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // 아래 doInBackground 함수 실행하기 전에, 해야할 일을, 여기에 작성.
            // 7. 프로그래스바가 보이게 하겠다.
        }

        // 1. 이 메소드 먼저 생성됨. (class 만들때 빨간줄 alt+enter)
        @Override
        protected Bitmap doInBackground(String... strings) {
            // 오래걸리는 일이나, 동시 작업이 필요한 일은 이 함수 안에 작성.
            // 8. 이미지 다운로드, bitmap => 이미지 처리
            String url = strings[0];
            Bitmap bitmap = null;
            // 네트워크 통해서, 이미지 파일을 비트맵으로 받아오는 코드.
            try {
                java.net.URL getUrl = new URL(url);
                InputStream inputStream = getUrl.openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {       // openStream 에서 catch 나면 여기로 옴
                e.printStackTrace();
            }
            return bitmap;
        }

        // 3. 이 메소드 만들어준다.
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            // 위의 doInBackground 함수가 다 실행되고 나서, 해야할 일을, 여기에 작성.
        }
    }
}


