package com.nerd.photos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.nerd.photos.model.Photos;

public class Second extends AppCompatActivity {

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        img = findViewById(R.id.img);
        Photos photos = (Photos) getIntent().getSerializableExtra("img");
        String url = photos.getUrl();

        // 23, 24 번줄 대체 => String url = getIntent().getStringExtra("url");


        GlideUrl glideUrl = new GlideUrl(url, new LazyHeaders.Builder().addHeader("User-Agent","Your-user-agent").build());
        Glide.with(Second.this).load(glideUrl).centerCrop().placeholder(R.drawable.ic_launcher_foreground).into(img);

    }
}
