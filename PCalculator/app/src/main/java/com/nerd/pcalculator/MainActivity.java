package com.nerd.pcalculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText percent;
    EditText number;
    Button button;
    TextView results;

    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        percent = findViewById(R.id.percent);
        number = findViewById(R.id.number);
        button = findViewById(R.id.button);
        results = findViewById(R.id.results);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String p1 = percent.getText().toString();
                String n1 = number.getText().toString();

                if (p1.isEmpty() || n1.isEmpty()){
                    Toast.makeText(MainActivity.this, "둘 다 숫자를 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                double p = Double.parseDouble(p1);
                double n = Double.parseDouble(n1);

                results.setText("Result = "+p*n/100);

                index = (index + 1) % 5;
                if (index == 0){
                    AlertDialog.Builder result = new AlertDialog.Builder(MainActivity.this);
                    result.setTitle(R.string.alert_title);
                    result.setMessage(R.string.alert_maessage);
                    result.setPositiveButton(R.string.alert_btn, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    // 유저가 화면의 다른곳을 눌러도,알러트다이얼로그가 없어지지 않게 한다..
                    result.setCancelable(false);
                    result.setNeutralButton("취소",null);
                    result.show();
                    return;
                }

            }
        });

    }
}