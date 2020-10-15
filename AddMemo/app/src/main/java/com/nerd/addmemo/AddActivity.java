package com.nerd.addmemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nerd.addmemo.data.DatabaseHandler;
import com.nerd.addmemo.model.Memo;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {

    EditText editTitle;
    EditText editMemo;
    Button btnSave;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        editTitle = findViewById(R.id.editTitle);
        editMemo =findViewById(R.id.editMemo);
        btnSave = findViewById(R.id.btnSave);
        db= new DatabaseHandler(AddActivity.this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTitle.getText().toString().trim();
                String memo = editMemo.getText().toString().trim();
                if (title.isEmpty() && memo.isEmpty()) {
                    Toast.makeText(AddActivity.this, "제목과 내용은 필수입니다.",
                            Toast.LENGTH_SHORT).show();
                    return;

                }
                if (title.isEmpty()) {
                    Toast.makeText(AddActivity.this, "제목을 입력하세요",
                            Toast.LENGTH_SHORT).show();
                    return;

                }
                if (memo.isEmpty()) {
                    Toast.makeText(AddActivity.this, "메모를 입력하세요",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                Memo new_memo = new Memo();
                new_memo.setTitle(title);
                new_memo.setMemo(memo);

                db.addMemo(new_memo);     //새로운 contact 만들어서 데이터를 저장
                Toast.makeText(AddActivity.this, "저장되었습니다", Toast.LENGTH_LONG).show();
                ArrayList<Memo> memoArrayList = db.getAllMemos();
                for (Memo contact : memoArrayList) {
                    Log.i("AddMemo","저장"+title);
                    finish();
                }
            }
        });
    }
}
