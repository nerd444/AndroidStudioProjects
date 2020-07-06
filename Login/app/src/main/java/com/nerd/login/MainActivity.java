package com.nerd.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

    EditText emailCheck;
    EditText pwCheck;
    Button btnNo;
    Button btnYes;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        editPW = findViewById(R.id.editPW);
        button = findViewById(R.id.button);
        btnLogin = findViewById(R.id.btnLogin);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = editEmail.getText().toString();
                String password = editPassword.getText().toString();
                String pw = editPW.getText().toString();

                // 이메일에 @ 있는지 확인
                if (email.contains("@") == false){
                    Toast.makeText(MainActivity.this, "이메일을 바르게 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                // 비밀번호 6자리 이상, 12자리 이하인지 확인
                if (password.length() < 6 || password.length() > 12){
                    Toast.makeText(MainActivity.this, "비밀번호 길이는 6자리 이상, 12자리 이하로 작성해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                // 비밀번호가 같은지 확인
                if (password.compareTo(pw) != 0){
                    Toast.makeText(MainActivity.this, "비밀번호를 일치하여 주십시오.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // SignUp 화면으로 Intent
                Intent i = new Intent(MainActivity.this, SignUpActivity.class);
                i.putExtra("email",email);
                i.putExtra("password",password);
                startActivity(i);

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createPopupDialog();

            }
        });

    }

    // 커스텀 다이얼로그
    public void createPopupDialog(){
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        View alertView = getLayoutInflater().inflate(R.layout.alert, null);     // alert.xml 에서 뷰를 가져와서 알러트 다이얼로그로 만듬.

        // 화면 연결, 1. 액티비티에 멤버변수로 뺴줌 2. 여기서 연결
        emailCheck = alertView.findViewById(R.id.emailCheck);
        pwCheck = alertView.findViewById(R.id.pwCheck);
        btnNo = alertView.findViewById(R.id.btnNo);
        btnYes = alertView.findViewById(R.id.btnYes);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailCheck.getText().toString().trim();
                String pw = pwCheck.getText().toString().trim();

                // 비어있는지 확인
                if (email.isEmpty() || pw.isEmpty()){
                    Toast.makeText(MainActivity.this, "이메일이나 비번을 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                dialog.cancel();
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        alert.setView(alertView);
        alert.setCancelable(false);

        dialog = alert.create();
        dialog.show();
    }
}
