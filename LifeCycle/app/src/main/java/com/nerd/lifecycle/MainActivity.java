package com.nerd.lifecycle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // 우리 눈에는 onCreate 함수만 있지만,
    // 실제로는 onCreate 실행 -> onStart 실행 -> onResume 실행을 시킨다.
    // 안드로이드가 시킨다.

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("MyLife","1. onCreate 함수 호출");

        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 다른 액티비티로 화면 전환하는 코드
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MyLife","2. onStart 함수 호출");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MyLife","3. onResume 함수 호출");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MyLife","4. onPause 함수 호출");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MyLife","5. onStop 함수 호출");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MyLife","6. onDestroy 함수 호출");
    }

    // 앱 종료할때 onPause -> onStop -> onDestroy

}
