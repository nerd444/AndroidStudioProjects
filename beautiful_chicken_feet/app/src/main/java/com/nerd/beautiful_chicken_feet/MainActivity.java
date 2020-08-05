package com.nerd.beautiful_chicken_feet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button reservation;
    Button login;
    Button address;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reservation = findViewById(R.id.reservation);
        login = findViewById(R.id.login);
        address = findViewById(R.id.address);
        signUp = findViewById(R.id.signUp);

    }
}