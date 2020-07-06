package com.nerd.youtube;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.nerd.youtube.model.Youtube;

public class Image extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        imageView = findViewById(R.id.imageView);

        Youtube youtube = (Youtube) getIntent().getSerializableExtra("Image");
        String image = youtube.getUrl();

        GlideUrl glideUrl = new GlideUrl(image, new LazyHeaders.Builder().addHeader("User-Agent","Your-user-agent").build());
        Glide.with(Image.this).load(glideUrl).centerCrop().placeholder(R.drawable.ic_launcher_foreground).into(imageView);

    }
}
