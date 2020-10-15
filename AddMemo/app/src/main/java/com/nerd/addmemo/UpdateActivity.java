package com.nerd.addmemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nerd.addmemo.data.DatabaseHandler;
import com.nerd.addmemo.model.Memo;

public class UpdateActivity extends AppCompatActivity {
    EditText updateTitle;
    EditText updateMemo;
    Button btnUpdate;
    int id ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        updateTitle = findViewById(R.id.updateTitle);
        updateMemo = findViewById(R.id.updateMemo);
        btnUpdate = findViewById(R.id.btnUpdate);

        // 어댑터의 카드뷰 클릭하면, 여기서 데이터를 받아준다.
        id = getIntent().getIntExtra("id",-1);
        String title= getIntent().getStringExtra("title");
        String memo = getIntent().getStringExtra("memo");
        // 화면에 표시.
        updateTitle.setText(title);
        updateMemo.setText(memo);


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = updateTitle.getText().toString().trim();
                String memo = updateMemo.getText().toString().trim();

                DatabaseHandler db= new DatabaseHandler(UpdateActivity.this);
                Memo updateMemo = db.getMemo(id);
                updateMemo.setTitle(title);
                updateMemo.setMemo(memo);          // 45~47 대신 Contact contact = new Contact(id, title, content) 라고 쓸수 있다. DB 선언 위에(43)
                db.updateMemo(updateMemo);

                Toast.makeText(UpdateActivity.this, "수정 되었습니다", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}

