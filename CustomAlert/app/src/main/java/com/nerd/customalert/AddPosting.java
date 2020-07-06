package com.nerd.customalert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nerd.customalert.adapter.RecyclerViewAdapter;
import com.nerd.customalert.model.CustomAlert;

import java.util.ArrayList;

public class AddPosting extends AppCompatActivity {

    EditText editTitle;
    EditText editBody;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_posting);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTitle = findViewById(R.id.editTitle);
        editBody = findViewById(R.id.editBody);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTitle.getText().toString().trim();
                String body = editBody.getText().toString().trim();

                Intent i = getIntent();
                // 객체 생성
                CustomAlert customAlert = new CustomAlert(0,0,title,body);
                i.putExtra("data",customAlert);
                setResult(RESULT_OK, i);

                if (title.isEmpty() || body.isEmpty()){
                    Toast.makeText(AddPosting.this, "데이터를 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                finish();
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
