package com.nerd.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                String e = minute.getText().toString();
                ei1 = Integer.parseInt(e);
                e1 = (long)ei1 * 1000;

                cdt = new CountDownTimer(e1, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        int remainingTime = (int)millisUntilFinished / 1000;
                        minute.setText(remainingTime);
                    }

                    @Override
                    public void onFinish() {

                    }
                };

                String d = cho.getText().toString();
                ei2 = Integer.parseInt(d);
                e2 = (long)ei2 * 1000;

                cdt = new CountDownTimer(e2, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        int remainingTime = (int)millisUntilFinished / 1000;
                        cho.setText(remainingTime);
                    }

                    @Override
                    public void onFinish() {

                    }
                };


            }
        });

    }
}
