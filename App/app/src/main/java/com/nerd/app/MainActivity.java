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

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    EditText time;
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

        time = findViewById(R.id.time);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        btnReset = findViewById(R.id.btnReset);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String edit_time = time.getText().toString();
//                ei1 = Integer.parseInt(edit_time);
//                e1 = (long)ei1 * 1000;

                // Time is in millisecond so 50sec = 50000 I have used
                // countdown Interveal is 1sec = 1000 I have used
                cdt = new CountDownTimer(50000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        // 숫자 형식 지정에 두 자리 수로만 사용
                        NumberFormat f = new DecimalFormat("00");
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                        long hour = (millisUntilFinished / 3600000) % 24;
                        long min = (millisUntilFinished / 60000) % 60;
                        long sec = (millisUntilFinished / 1000) % 60;
//                        f.format(hour) + ":" + f.format(min) + ":" + f.format(sec)
                        time.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
                    }
                    // When the task is over it will print 00:00:00 there
                    public void onFinish() {
                        time.setText("00:00:00");
                    }
                }.start();

//                // 60초 마다 -1분 코드작성
//
//                String e = minute.getText().toString();
//                ei1 = Integer.parseInt(e);
//                e1 = (long)ei1 * 1000;
//
//                cdt = new CountDownTimer(e1, 1000) {
//                    @Override
//                    public void onTick(long millisUntilFinished) {
//                        int remainingTime = (int)millisUntilFinished / 1000;
//                        minute.setText(""+remainingTime);
//                        minute.setCursorVisible(false);
//                        cho.setCursorVisible(false);
//                    }
//
//                    @Override
//                    public void onFinish() {
//                        minute.setText("");
//                        cho.setText("");
//                        minute.setCursorVisible(true);
//                        cho.setCursorVisible(true);
//                    }
//                };
//
//                String d = cho.getText().toString();
//                ei2 = Integer.parseInt(d);
//
//                // 초는 60초까지 제한
//                if (ei2 > 60){
//                    Toast.makeText(MainActivity.this, "60초이상은 분으로 써주세요", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                e2 = (long)ei2 * 1000;
//
//                cdt = new CountDownTimer(e2, 1000) {
//                    @Override
//                    public void onTick(long millisUntilFinished) {
//                        int remainingTime = (int)millisUntilFinished / 1000;
//                        cho.setText(""+remainingTime);
//                        minute.setCursorVisible(false);
//                        cho.setCursorVisible(false);
//                    }
//
//                    @Override
//                    public void onFinish() {
//                        minute.setText("");
//                        cho.setText("");
//                        minute.setCursorVisible(true);
//                        cho.setCursorVisible(true);
//                    }
//                };
//
//                cdt.start();
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
                time.setText("00:00:00");
            }
        });

    }
}
