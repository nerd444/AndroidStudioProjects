package com.nerd.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {

    TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        welcome = findViewById(R.id.welcome);

        // 메인 액티비티로부터, 데이터를 받는 코드.
        String email = getIntent().getStringExtra("email");
        // 위 두줄 또는 ==> String email = getIntent().getStringExtra("email");
        welcome.setText(email+" 님 회원가입을 축하합니다.");

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
