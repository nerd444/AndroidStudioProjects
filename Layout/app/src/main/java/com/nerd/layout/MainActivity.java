package com.nerd.layout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView2);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 버튼 눌렸을 때 해야 될 일을 여기에 작성
                // 1. 에디트텍스트에 적혀있는 글자 가져오기
                String catYearEdit = editText.getText().toString();
                Log.i("Cat","유저가 입력한 값은 : " + catYearEdit);
                //2. 이번년도에서, 가지고온 년도를 뺀다.
                int catAgeText = 2020 - Integer.parseInt(catYearEdit);
                Log.i("CatAge","계산한 나이는 : "+ catAgeText);
                //3. 텍스트뷰에 표시
                textView.setText(""+catAgeText);
                //4. 에디트텍스트에서 유저가 쓴 글자를 지운다.
                editText.setText("  ");
            }
        });
    }
}
