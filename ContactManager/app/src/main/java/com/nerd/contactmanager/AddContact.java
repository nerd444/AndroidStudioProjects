package com.nerd.contactmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nerd.contactmanager.data.DatabaseHandler;
import com.nerd.contactmanager.model.Contact;

public class AddContact extends AppCompatActivity {

    EditText editName;
    EditText editPN;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        editName = findViewById(R.id.editName);
        editPN = findViewById(R.id.editPhone);
        btnSave = findViewById(R.id.btnSave);

        // 버튼을 누르면, 주소록에 저장.
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. 에디트텍스트에서 유저가 입력한, 이름과 폰번을 가져온다.
                String name = editName.getText().toString().trim();
                String pn = editPN.getText().toString().trim();

                if (name.isEmpty() || pn.isEmpty()){
                    Toast.makeText(AddContact.this,"이름이나 연락처는 필수입니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 2. 데이터베이스에 저장한다.
                // 2-1. 핸들러 클래스를 객체 생성한다.
                DatabaseHandler db = new DatabaseHandler(AddContact.this);
                // 2-2. Contact 객체 생성해서, 이름과 폰번을 담는다.
                Contact new_contact = new Contact();
                new_contact.setName(name);
                new_contact.setPhone_number(pn);
                Log.i("myDB","plus addContact.");
                // 2-3. 데이터베이스에 저장하는 메소드 호출
                db.addContact(new_contact);
                // 3. 토스트메세지를 보여줘라.
                Toast.makeText(AddContact.this,"잘 저장되었습니다.", Toast.LENGTH_SHORT).show();
                //4. 메인액티비티로 돌아가라 => 밑에 숨어있는 메인 액티비티가 보이도록 하라
                // => 지금의 액티비티를 종료하라.
                finish();
            }
        });

    }


}
