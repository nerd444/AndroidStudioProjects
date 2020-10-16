package com.nerd.contactmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nerd.contactmanager.adapter.RecyclerViewAdapter;
import com.nerd.contactmanager.data.DatabaseHandler;
import com.nerd.contactmanager.model.Contact;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // 수정 다이얼로그
    EditText editName;
    EditText editPhone;
    Button btnSave;
    Button btnCancle;

    int id;
    AlertDialog dialog;
    public static Context context;

    RecyclerView recyclerView;      // 메인 화면에 있는 리사이클러 뷰    (이거부터아래 세개는 세트임.)
    RecyclerViewAdapter recyclerViewAdapter;        // 우리가 만든, 하나의 셀을 연결시키는 어댑터
    ArrayList<Contact> contactArrayList;        // 데이터베이스에서 읽어온 주소록 정보를 저장할 리스트 (나중에 네트워크 연결해서 가져올거임)

    // 앱이 실행되면 CPU 가 처음 실행되는 곳
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 리사이클러뷰 연결하고, 기본적인 셋팅.
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        createPopupDialog();

    }

    @Override
    protected void onResume() {
        super.onResume();
        // 데이터베이스에서 테이블에 저장된 데이터 읽어서, 어레이리스트에 저장.       // 원래 60번줄까지  37과 39사이에 있었는데 onResume 안으로 와야 추가하고 메인에 왔을때 바로 추가가됌. 그 전에는 다시켜야지 떴음.
        DatabaseHandler db = new DatabaseHandler(MainActivity.this);
        contactArrayList = db.getAllContacts();

        // 우리가 만든 하나의 셀 표시하는 어댑터를 생성해서, 리사이클러뷰에 연결
        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, contactArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    // 메인액티비티에, refresh 메소드 우리가 하나 만듬. 데이터베이스에서 정보 가져와서, 화면 갱신
    public void refresh(){
        DatabaseHandler db = new DatabaseHandler(MainActivity.this);
        contactArrayList = db.getAllContacts();
        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, contactArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menus, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.add_contact){
            // 새로운 액티비티 실행.
            Intent i = new Intent(MainActivity.this,AddContact.class);
            startActivity(i);
            return true;
        }else if (id == R.id.trash_can){

        }

        return super.onOptionsItemSelected(item);
    }

    public void createPopupDialog(){
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        View alertView = getLayoutInflater().inflate(R.layout.activity_update_contact,null);

        editName = findViewById(R.id.editName);
        editPhone = findViewById(R.id.editPhone);
        btnSave = findViewById(R.id.btnSave);
        btnCancle = findViewById(R.id.btnCancle);

        // 어댑터에서, 유저가 클릭한 경우, 데이터를 받아온다.
        id = getIntent().getIntExtra("id",-1);
        String n = getIntent().getStringExtra("name");
        String p = getIntent().getStringExtra("phone");
        // 위에서 받아온 데이터를, 에디트 텍스트에 보여준다.
        editName.setText(n);
        editPhone.setText(p);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 데이터베이스에 업데이트.
                // 유저가 변경했을 수 있는 이름과 폰번을, 에디트 텍스트에서 가져온다.
                String name = editName.getText().toString().trim();
                String phone = editPhone.getText().toString().trim();

                // 디비핸들러 클래스를 통해서 업데이트
                DatabaseHandler db = new DatabaseHandler(MainActivity.this);
                Contact contact = new Contact(id, name, phone);
                db.updateContact(contact);
                // 토스트를 보여준다.
                Toast.makeText(MainActivity.this, "수정되었습니다.",Toast.LENGTH_SHORT).show();
                // 이 액티비티 종료.
                finish();

                dialog.cancel();
            }
        });

        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        alert.setView(alertView);
        alert.setCancelable(false);

        dialog = alert.create();
        dialog.show();
    }

}
