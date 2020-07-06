package com.nerd.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    TextView txtTime;
    EditText txtGet;
    Button btnC;
    Button btnS;

    CountDownTimer timer;
    int sec;
    long millisec;

    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = findViewById(R.id.img);
        txtTime = findViewById(R.id.txtTime);
        txtGet = findViewById(R.id.txtGet);
        btnC = findViewById(R.id.btnC);
        btnS = findViewById(R.id.btnS);

        mp = MediaPlayer.create(this,R.raw.alarm);


        btnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String secStr = txtGet.getText().toString().trim();
                sec = Integer.parseInt(secStr);
                millisec = (long)sec * 1000;

                timer = new CountDownTimer(millisec,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        int remainingTime = (int)millisUntilFinished / 1000;
                        txtTime.setText(""+remainingTime);
                    }

                    @Override
                    public void onFinish() {
                        // 타이머 완료시, 소리와 애니메이션

                        mp.start();
                        YoYo.with(Techniques.Tada)
                                .duration(700)
                                .repeat(5)
                                .playOn(findViewById(R.id.img));

                    }
                };

                timer.start();
            }
        });

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                txtGet.setText(""+sec);
            }
        });


    }
}
