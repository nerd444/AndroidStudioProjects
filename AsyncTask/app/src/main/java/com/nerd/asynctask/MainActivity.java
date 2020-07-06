package com.nerd.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = findViewById(R.id.img);
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d7/Android_robot.svg/2000px-Android_robot.svg.png";


                // Glide 로 받아오기
                Glide.with(MainActivity.this).load(url).centerCrop().placeholder(R.drawable.ic_launcher_foreground).into(img);

//                DownloadImageTask downloadImageTask = new DownloadImageTask(MainActivity.this);
//                // String(url) 받아올 부분
//                downloadImageTask.execute(url);
            }
        });

    }
    // 이미지를 네트워크 통해서 다운로드 하여, 화면에 표시할 쓰레드.
    // 쓰레드란? 동시에 여러가지 작업을 가능하게 해주는 것.
    // 이러한 쓰레드를, 사용하기 편하게, 안드로이드에서 제공해 주는 클래스가,
    // AsyncTask 어씽크 태스크 (AsyncTask = 내부적으로 Volley 가 라이브러리에 이미 들어있음.)
    // AsyncTask<String, Void, Bitmap> <> 이 안은 프로그램마다 다 다르다.
    // String 부분은 doInBackground 파라메터부분, Bitmap 은 doInBackground 리턴부분(onPostExecute), Void 는 onProgressUpdate 부분 (update 함수가 없어서 꼭 써야함)
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap>{
        // 4. 멤버변수 설정
        ProgressBar progressBar;
        Context context;

        // 5. 생성자
        public DownloadImageTask(Context context) {
            this.context = context;
            // 6. 프로그래스바 연결
            progressBar = findViewById(R.id.progressBar);
        }

        // 2. 이 메소드 만들어준다.
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // 아래 doInBackground 함수 실행하기 전에, 해야할 일을, 여기에 작성.
            // 7. 프로그래스바가 보이게 하겠다.
            progressBar.setVisibility(View.VISIBLE);
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
                URL getUrl = new URL(url);
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
            progressBar.setVisibility(View.GONE);
            img.setImageBitmap(bitmap);
        }
    }

}
