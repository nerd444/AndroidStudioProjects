package com.nerd.mytest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView txtTitle;
    EditText editName;
    EditText phone;
    ImageView imgCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTitle = findViewById(R.id.txtTitle);
        editName = findViewById(R.id.editName);
        phone = findViewById(R.id.phone);
        imgCenter = findViewById(R.id.imgCenter);

        // 버튼을 클릭하면, 로그에 "버튼 클릭 됨" 을 찍도록 코드 작성
        Button btnClick = findViewById(R.id.btnClick);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("MyTest","버튼 클릭 ok");
                String text = txtTitle.getText().toString();
                Log.i("MyTest","get test : " + text);

                String name = editName.getText().toString();
                String phoneNumber = phone.getText().toString();

                txtTitle.setText(name + "\n" + phoneNumber);

                Toast.makeText(MainActivity.this, name+"\n"+phone,Toast.LENGTH_LONG).show();

                imgCenter.setImageResource(R.drawable.chicks);

            }

        });

    }
}
