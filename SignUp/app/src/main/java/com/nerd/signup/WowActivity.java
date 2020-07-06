package com.nerd.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class WowActivity extends AppCompatActivity {

    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wow);

        txt = findViewById(R.id.txt);
        String email = getIntent().getStringExtra("email");
        txt.setText(email+" 님 환영합니다.");

    }
}
