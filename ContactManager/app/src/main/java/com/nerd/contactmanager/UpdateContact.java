package com.nerd.contactmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nerd.contactmanager.adapter.RecyclerViewAdapter;
import com.nerd.contactmanager.data.DatabaseHandler;
import com.nerd.contactmanager.model.Contact;

import java.util.ArrayList;

public class UpdateContact extends AppCompatActivity {

    EditText editName;
    EditText editPhone;
    Button btnSave;

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);

        editName = findViewById(R.id.editName);
        editPhone = findViewById(R.id.editPhone);
        btnSave = findViewById(R.id.btnSave);

        // 어댑터에서, 유저가 클릭한 경우, 데이터를 받아온다.
        id = getIntent().getIntExtra("id",-1);
        String n = getIntent().getStringExtra("name");
        String p = getIntent().getStringExtra("phone");
        // 위에서 받아온 데이터를, 에디트 텍스트에 보여준다.
        editName.setText(""+n);
        editPhone.setText(""+p);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 데이터베이스에 업데이트.
                // 유저가 변경했을 수 있는 이름과 폰번을, 에디트 텍스트에서 가져온다.
                String name = editName.getText().toString().trim();
                String phone = editPhone.getText().toString().trim();
                // 디비핸들러 클래스를 통해서 업데이트
                DatabaseHandler db = new DatabaseHandler(UpdateContact.this);
                Contact contact = new Contact(id, name, phone);
                db.updateContact(contact);
                // 토스트를 보여준다.
                Toast.makeText(UpdateContact.this, "수정되었습니다.",Toast.LENGTH_SHORT).show();
                // 이 액티비티 종료.
                finish();
            }
        });

    }
}
