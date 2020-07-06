package com.nerd.serializable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.nerd.serializable.model.Person;

import org.w3c.dom.Text;

public class Second extends AppCompatActivity {

    TextView txtPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        txtPerson = findViewById(R.id.txtPerson);

        Person person = (Person) getIntent().getSerializableExtra("PersonClass");
        String info = person.getName()+", "+person.getEmail()+", "+person.isMale();

        txtPerson.setText(info);

    }
}
