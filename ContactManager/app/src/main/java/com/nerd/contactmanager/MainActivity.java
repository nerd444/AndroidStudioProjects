package com.nerd.contactmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

}
