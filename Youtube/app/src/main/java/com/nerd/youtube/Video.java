package com.nerd.youtube;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.nerd.youtube.model.Youtube;

public class Video extends AppCompatActivity {

    WebView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        video = findViewById(R.id.video);
        Youtube youtube = (Youtube) getIntent().getSerializableExtra("youtube");
        String videoId = youtube.getVideoId();
        String v = "https://www.youtube.com/watch?v="+videoId;

        openWebPage(v);
        finish();

    }

    public void openWebPage(String url){
        Uri webpage = Uri.parse(url);
        Intent i = new Intent(Intent.ACTION_VIEW, webpage);
        if (i.resolveActivity(getPackageManager()) != null){
            startActivity(i);
        }
    }


}
