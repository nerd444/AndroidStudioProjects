package com.nerd.app;

import androidx.appcompat.app.AppCompatActivity;

import android.opengl.Visibility;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText minute;
    EditText cho;
    Button btnStart;
    Button btnStop;
    Button btnReset;

    int ei1;
    int ei2;

    long e1;
    long e2;

    CountDownTimer cdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        minute = findViewById(R.id.minute);
        cho = findViewById(R.id.cho);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        btnReset = findViewById(R.id.btnReset);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 60초 마다 -1분 코드작성

                String e = minute.getText().toString();
                ei1 = Integer.parseInt(e);
                e1 = (long)ei1 * 1000;

                cdt = new CountDownTimer(e1, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        int remainingTime = (int)millisUntilFinished / 1000;
                        minute.setText(""+remainingTime);
                        minute.setCursorVisible(false);
                        cho.setCursorVisible(false);
                    }

                    @Override
                    public void onFinish() {
                        minute.setText("");
                        cho.setText("");
                        minute.setCursorVisible(true);
                        cho.setCursorVisible(true);
                    }
                };

                String d = cho.getText().toString();
                ei2 = Integer.parseInt(d);

                // 초는 60초까지 제한
                if (ei2 > 60){
                    Toast.makeText(MainActivity.this, "60초이상은 분으로 써주세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                e2 = (long)ei2 * 1000;

                cdt = new CountDownTimer(e2, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        int remainingTime = (int)millisUntilFinished / 1000;
                        cho.setText(""+remainingTime);
                        minute.setCursorVisible(false);
                        cho.setCursorVisible(false);
                    }

                    @Override
                    public void onFinish() {
                        minute.setText("");
                        cho.setText("");
                        minute.setCursorVisible(true);
                        cho.setCursorVisible(true);
                    }
                };

                cdt.start();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cdt.cancel();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cdt.cancel();
                minute.setText("");
                cho.setText("");
                minute.setCursorVisible(true);
                cho.setCursorVisible(true);
            }
        });

    }
}
