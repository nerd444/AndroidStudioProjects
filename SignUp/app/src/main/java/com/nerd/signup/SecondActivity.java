package com.nerd.signup;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    ImageView img;
    Button btnGood;
    Button btnNo;
    Button btnCheck;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        btnGood = findViewById(R.id.btnGood);
        btnNo = findViewById(R.id.btnNo);
        btnCheck = findViewById(R.id.btnCheck);
        img = findViewById(R.id.img);

        btnGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.setImageResource(R.drawable.yes);
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.setImageResource(R.drawable.no);
            }
        });

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder finishAlert = new AlertDialog.Builder(SecondActivity.this);
                finishAlert.setTitle("회원가입 완료");
                finishAlert.setMessage("완료하시겠습니까?");
                finishAlert.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                finishAlert.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(SecondActivity.this, WelcomeActivity.class);

                        // 이메일 넘겨받는 코드
                        Intent e = getIntent();
                        String getE = e.getStringExtra("EMAIL");        //  두줄 or 클릭 위에 String getE; 클릭 아래 getE = getIntent().getStringExtra("email");
                        String getP = e.getStringExtra("PASSWORD");
                        i.putExtra("email",getE);
                        i.putExtra("password",getP);
                        startActivity(i);
                        finish();
                    }
                });
                finishAlert.setCancelable(false);
                finishAlert.show();
            }
        });

    }
}

