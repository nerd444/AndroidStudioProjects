package com.nerd.getdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    TextView txtData;
    EditText editSend;
    Button btnThird;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        txtData = findViewById(R.id.txtData);
        editSend = findViewById(R.id.editSend);
        btnThird = findViewById(R.id.btnThird);
        // 1. 인텐트에 담긴 정보를 가져와서, 텍스트뷰에 표시
        Intent i =getIntent();
        String data = i.getStringExtra("data");
        int hiddenData = i.getIntExtra("hiddenData", 0);
        txtData.setText(data);

        // 버튼 누르면, 메인 액티비티로 정보를 전송하고, 이 액티비티는 종료
        btnThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = editSend.getText().toString().trim();
                Intent i = getIntent();
                i.putExtra("ThirdData", data);
                setResult(RESULT_OK, i);
                finish();
            }
        });

    }
}
