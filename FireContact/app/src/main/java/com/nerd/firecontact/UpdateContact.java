package com.nerd.firecontact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.nerd.firecontact.model.Contact;

public class UpdateContact extends AppCompatActivity {

    EditText editName;
    EditText editPhone;
    Button btnUpdate;
    String id;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);

        editName = findViewById(R.id.editName);
        editPhone = findViewById(R.id.editPhone);
        btnUpdate = findViewById(R.id.btnUpdate);

        Contact contact = (Contact) getIntent().getSerializableExtra("Contact");
        id = contact.getId();
        String name = contact.getName();
        String phone_number = contact.getPhone_number();

        editName.setText(name);
        editPhone.setText(phone_number);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString().trim();
                String phone = editPhone.getText().toString().trim();

                Contact contact = new Contact(name, phone);
                db.collection("Contact").document(id).set(contact).
                        addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(UpdateContact.this,
                                        "수정 되었습니다.",
                                        Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
                finish();
            }
        });
    }
}
