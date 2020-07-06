package com.nerd.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class WelcomeActivity extends AppCompatActivity {

    TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        welcome = findViewById(R.id.welcome);

        // 세컨드 액티비티로부터, 데이터를 받는 코드.
        String email = getIntent().getStringExtra("email");
        String password = getIntent().getStringExtra("password");
        // 위 두줄 또는 ==> String email = getIntent().getStringExtra("email");

        welcome.setText(email+" 님 회원가입을 축하합니다.");

        // 이메일과 패스워드를, 쉐어드 프리퍼런스에 저장한다.
        SharedPreferences sp = getSharedPreferences("regist_pref",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("email", email);
        editor.putString("password", password);
        editor.apply();

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
