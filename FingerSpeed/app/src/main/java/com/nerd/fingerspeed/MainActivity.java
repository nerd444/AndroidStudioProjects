package com.nerd.fingerspeed;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // 상수 선언 , final 하고 static 은 위치가 바뀌어도 된다.
    public final static int TAB_COUNT = 100;

    TextView txtTimer;
    TextView txtCount;
    Button btnTap;
    TextView cheatKey;
    // 타이머 멤버변수
    CountDownTimer countDownTimer;
    long initCountMillis = 30000;
    int timerInterval = 1000;
    int tabCount = TAB_COUNT;
    int remainingTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTimer = findViewById(R.id.txtTimer);
        txtCount = findViewById(R.id.txtCount);
        btnTap = findViewById(R.id.btnTap);
        cheatKey = findViewById(R.id.cheatKey);
        // 누르면 10씩 줄지만, 남은카운트가 10미만이면 누를 수 없는 치트키.
        cheatKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tabCount > 10){
                    tabCount = tabCount - 10;
                    txtCount.setText(""+tabCount);
                }
            }
        });

        btnTap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. 타이머 남은 시간이 0인지 체크해서, 0이면 아래 실행 안하면 된다.
                // 2. 탭수가 0인지 체크해서, 0이면 아래로 실행 안하면 된다.
                // 3. 숫자 감소시키고,
                // 4. 감소된 숫자를 화면에 표시.
                // 5. 성공하였습니다. 토스트 처리.
                if (remainingTime == 0){
                    return;
                }
                if (tabCount == 0){
                    return;
                }

                tabCount = tabCount - 1;
                txtCount.setText(""+tabCount);

                if (tabCount == 0){
                    // 타이머 멈추기
                    countDownTimer.cancel();

                    Toast.makeText(MainActivity.this, "성공하였습니다.", Toast.LENGTH_SHORT).show();

                    String message = "기록은 " + (initCountMillis/1000 - remainingTime) + "초 입니다. 다시 도전하시겠습니까?";
                    showMyAlert("성공",message);
                }

            }
        });

        countDownTimer = new CountDownTimer(initCountMillis,timerInterval) {       //60초면 60000
            @Override
            public void onTick(long millisUntilFinished) {
                remainingTime = (int)millisUntilFinished / 1000;
                Log.i("Finger","남은 시간 : " + remainingTime);
                txtTimer.setText(""+remainingTime);
            }

            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this, "시간이 종료되었습니다.", Toast.LENGTH_SHORT).show();
                // 알러트 다이얼로 만든다.
                // 1. 실패한경우
                if (tabCount > 0){
                    showMyAlert("실패","다시 도전하시겠습니까?");
                }
            }
        };

        countDownTimer.start();

        txtCount.setText(""+tabCount);
    }

    // 알러트다이얼 함수만들기 = 성공시, 실패시 두번을 써야돼기 때문에 바뀌는 두 부분만 바꿔줄 수 있도록 함수 설계.
    void showMyAlert(String title, String message){
        AlertDialog.Builder finishAlert = new AlertDialog.Builder(MainActivity.this);
        finishAlert.setTitle(title);
        finishAlert.setMessage(message);
        finishAlert.setPositiveButton("네", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                countDownTimer.start();
                tabCount = 100;
                txtCount.setText(""+tabCount);
            }
        });
        finishAlert.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 앱 종료 코드
                finish();
            }
        });
        finishAlert.setCancelable(false);
        finishAlert.show();
    }

}
