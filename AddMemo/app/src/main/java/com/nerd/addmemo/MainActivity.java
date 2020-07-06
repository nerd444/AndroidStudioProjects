package com.nerd.addmemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.nerd.addmemo.adapter.RecyclerViewAdapter;
import com.nerd.addmemo.data.DatabaseHandler;
import com.nerd.addmemo.model.Contact;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnAdd;
    EditText editSelect;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<Contact> contactArrayList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        editSelect = findViewById(R.id.editSelect);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        DatabaseHandler db = new DatabaseHandler(MainActivity.this);
        contactArrayList = db.getAllContacts();

        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, contactArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(i);
            }
        });

        editSelect.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String select = editSelect.getText().toString().trim();
                DatabaseHandler db =new DatabaseHandler(MainActivity.this);
                contactArrayList = db.getLike(select);

                // 73,74 => 데이터가 바뀐걸 알려주는거
                recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, contactArrayList);
                recyclerView.setAdapter(recyclerViewAdapter);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
    @Override
    protected void onResume() {
        super.onResume();

        DatabaseHandler db = new DatabaseHandler(MainActivity.this);
        contactArrayList = db.getAllContacts();
        // 어댑터를 연결해야지 화면에 표시가 됨.
        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, contactArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
