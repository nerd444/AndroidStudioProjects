package com.nerd.firecontact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.nerd.firecontact.model.Contact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddContact extends AppCompatActivity {

    EditText editName;
    EditText editPhone;
    Button btnSave;

    public static final String COLLECTION_NAME = "Contact";
    public static final String KEY_THOUGHT = "thought";

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    ArrayList<Contact> contactArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        editName = findViewById(R.id.editName);
        editPhone = findViewById(R.id.editPhone);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString().trim();
                String phone = editPhone.getText().toString().trim();

//                Map<String, Object> contact = new HashMap<>();
//                contact.put("name", name);
//                contact.put("phone_number", phone);

                final Contact contact = new Contact(name, phone);
                db.collection("Contact").add(contact)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                contactArrayList.add(contact);
                                Log.i("TAG",documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });
                finish();
            }
        });

    }
}
