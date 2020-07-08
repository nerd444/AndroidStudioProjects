package com.nerd.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.nerd.user.model.SignUp;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editEmail;
    EditText editPassword;
    EditText editPw;
    Button btnSignUp;
    Button btnLogin;

    String email;
    String password;
    String pw;

    String loadEmail;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        editPw = findViewById(R.id.editPw);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogin = findViewById(R.id.btnLogin);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = editEmail.getText().toString().trim();
                password = editPassword.getText().toString().trim();
                pw = editPw.getText().toString().trim();

                //불러오기
                db.collection("SignUp").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                for (QueryDocumentSnapshot snapshots : queryDocumentSnapshots){
                                    String id = snapshots.getId();
                                    Log.i("AAA", id);
                                    SignUp signUp = snapshots.toObject(SignUp.class);
                                    // 저장된 이메일 가져와서
                                    loadEmail = signUp.getEmail();

                                    // edit email 이랑 같으면 토스트 띄우고 리턴
                                    if (email.compareTo(loadEmail) != 1) {
                                        Toast.makeText(MainActivity.this, "이미 존재하는 이메일입니다.", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                    SignUp signup = new SignUp(email, password);
                                    db.collection("SignUp").add(signup)
                                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                @Override
                                                public void onSuccess(DocumentReference documentReference) {
                                                    editEmail.setText("");
                                                    editPassword.setText("");
                                                    editPw.setText("");
                                                    Toast.makeText(MainActivity.this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {

                                                }
                                            });


                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Login.class);
                startActivity(i);
            }
        });

    }
}
