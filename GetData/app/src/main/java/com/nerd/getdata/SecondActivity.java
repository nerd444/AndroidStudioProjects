package com.nerd.getdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity {

    TextView txtData;
    EditText editSend;
    Button btnSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        txtData = findViewById(R.id.txtData);
        editSend = findViewById(R.id.editSend);
        btnSecond = findViewById(R.id.btnSecond);
        // 1. 첫번째 액티비티로 부터 데이터를 받아온다.
        Intent i =getIntent();
        String data = i.getStringExtra("data");
        int hiddenData = i.getIntExtra("hiddenData", 0);
        // 2. 데이터를 텍스트뷰에 표시.
        txtData.setText(data);

        // 3.0 버튼 눌렀을때...
        // 3.1 데이터를 보내기 위해서, 에디트 텍스트에서 문자열 가져온다.
        // 3.2 앞의 액티비티로 데이터 전달
        // 3.3 finish() 한다. : 이 액티비티 종료

        btnSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = editSend.getText().toString().trim();
                Intent i = getIntent();
                i.putExtra("secondData", data);
                setResult(RESULT_OK, i);
                finish();
            }
        });

    }
}
