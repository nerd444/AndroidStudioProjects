package com.nerd.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText loginEmail;
    EditText loginPW;
    Button btnLogin;
    CheckBox checkAutoLogin;

    String savedEmail;
    String savedPassword;
    // 쉐어드 프리퍼런스를 멤버변수로 뺀다.
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmail = findViewById(R.id.loginEmail);
        loginPW = findViewById(R.id.loginPW);
        btnLogin = findViewById(R.id.btnLogin);
        checkAutoLogin = findViewById(R.id.checkAutoLogin);

        sp = getSharedPreferences("regist_pref", MODE_PRIVATE);
        savedEmail = sp.getString("email", null);
        savedPassword = sp.getString("password", null);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 에디트 텍스트에 유저가 입력한 이메일과 비번을 가져와서
                // 쉐어드프리퍼런스에 저장되어 있던, 저장된 이메일과 비번을 서로 비교.
                String email = loginEmail.getText().toString().trim();
                String password = loginPW.getText().toString().trim();

                if (savedEmail != null && savedPassword != null && savedEmail.equals(email) && savedPassword.equals(password)) {        // equalsIgnore 어쩌구 => 대소문자 상관없이 맞는것.
                    // 로그인 완료화면 만들어서, 이메일 정보를 전달해 준다.

                    // 자동로그인 정보를 가져온다. 체크박스에서 가져온다. 체크 되었는지, 안되었는지.
                    if (checkAutoLogin.isChecked()) {
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putBoolean("auto_login",true);
                        editor.apply();
                    }else{
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putBoolean("auto_login",false);
                        editor.apply();
                    }

                    Intent i = new Intent(LoginActivity.this, WowActivity.class);
                    i.putExtra("email", email);
                    startActivity(i);
                    finish();
                } else if (savedPassword != password && savedEmail.equals(email)) {
                    Toast.makeText(LoginActivity.this, "비밀번호가 맞지 않습니다.", Toast.LENGTH_SHORT).show();
                } else if (savedEmail != email && savedPassword.equals(password)) {
                    Toast.makeText(LoginActivity.this, "이메일이 맞지 않습니다.", Toast.LENGTH_SHORT).show();
                } else if (savedEmail != email && savedPassword != password) {
                    Toast.makeText(LoginActivity.this, "이메일과 비밀번호가 맞지 않습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 1. 자동로그인이, 쉐어프리퍼런스에 지정되어 있는지정보를 가3져온다.
        boolean autoLogin = sp.getBoolean("auto_login", false);
        // 2. 자동로그인이 true 로 되어 있으면, 이메일과 비밀번호를 에디트텍스트에 표시
        // 3. 체크박스에도, 체크표시를 합니다.
        if (autoLogin == true){
            loginEmail.setText(savedEmail);
            loginPW.setText(savedPassword);
            checkAutoLogin.setChecked(true);
        }

    }
}
