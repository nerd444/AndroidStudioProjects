package com.nerd.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editEmail;
    EditText editPassword;
    EditText editPW;
    Button button;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editEmail = findViewById(R.id.loginEmail);
        editPassword = findViewById(R.id.editPassword);
        editPW = findViewById(R.id.editPW);
        button = findViewById(R.id.button);
        btnLogin = findViewById(R.id.btnLogin);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. 이메일주소, 비번1,비번2, 값 다 가져오기.
                // 2. 이메일 맞는지 체크
                // 3. 비밀번호 길이 체크
                // 4. 비밀번호 2개 일치하는지 체크
                // 5. 다 이상없으면, 다음 엑티비티로 넘어감.

                String email = editEmail.getText().toString();
                String password = editPassword.getText().toString();
                String pw = editPW.getText().toString();

                if (email.contains("@") == false){
                    Toast.makeText(MainActivity.this, "이메일 형식에 맞게 작성해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6 || password.length() > 12){
                    Toast.makeText(MainActivity.this, "비밀번호 길이는 6자리 이상, 12자리 이하로 작성해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.compareTo(pw) != 0){
                    Toast.makeText(MainActivity.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 두번째 액티비티 만든 후, 이 코드 작성.
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                i.putExtra("EMAIL",email);
                i.putExtra("PASSWORD",password);
                startActivity(i);
                // 현재 액티비티를 종료.
                finish();

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i  = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                finish();

            }
        });

    }
}
