package com.nerd.edit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        // 텍스트 와쳐
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {        // CharSequence = 문자열비슷한건데 문자열은 아니여서, toString 을 붙여줘야함.
                Toast.makeText(MainActivity.this, "덱스트 바뀌기 전 : "+s.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Toast.makeText(MainActivity.this, "덱스트가 바뀌고 있음 : "+s.toString(), Toast.LENGTH_SHORT).show();
                textView.setText(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                Toast.makeText(MainActivity.this, "덱스트 바뀐 후!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
