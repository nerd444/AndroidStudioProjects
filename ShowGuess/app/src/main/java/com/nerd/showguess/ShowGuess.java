package com.nerd.showguess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ShowGuess extends AppCompatActivity {

    TextView txtRecv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_guess);

        txtRecv = findViewById(R.id.txtRecv);

        Intent i = getIntent();
        String guess = i.getStringExtra("key");
        int number = i.getIntExtra("number,",0); // number가 없으면 무조건 0으로 처리하겠다.
        String myStr = i.getStringExtra("str");
        boolean myBoo = i.getBooleanExtra("boo", false);

        String all = guess + " " + number + " " + myStr + " " + myBoo;

        txtRecv.setText(all);

    }
}
