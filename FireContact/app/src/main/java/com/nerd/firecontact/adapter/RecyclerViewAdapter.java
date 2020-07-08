package com.nerd.firecontact.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.nerd.firecontact.MainActivity;
import com.nerd.firecontact.R;
import com.nerd.firecontact.UpdateContact;
import com.nerd.firecontact.model.Contact;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    Context context;
    ArrayList<Contact> contactArrayList;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public RecyclerViewAdapter(Context context, ArrayList<Contact> contactArrayList) {
        this.context = context;
        this.contactArrayList = contactArrayList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Contact contact = contactArrayList.get(position);
        String name = contact.getName();
        String phone = contact.getPhone_number();

        holder.txtName.setText(name);
        holder.txtPhone.setText(""+phone);

    }

    @Override
    public int getItemCount() {
        return contactArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtName;
        public TextView txtPhone;
        public ImageView imgDelete;
        public CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtPhone = itemView.findViewById(R.id.txtPhone);
            imgDelete = itemView.findViewById(R.id.imgDelete);
            cardView = itemView.findViewById(R.id.cardView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Contact contact = contactArrayList.get(getAdapterPosition());
                    Intent i = new Intent(context, UpdateContact.class);
                    i.putExtra("Contact", contact);
                    context.startActivity(i);
                }
            });

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder finishAlert = new AlertDialog.Builder(context);
                    finishAlert.setTitle("주소록 삭제");
                    finishAlert.setMessage("정말 삭제하시겠습니까?");
                    finishAlert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                        int index = getAdapterPosition();
                        Contact contact = contactArrayList.get(index);
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // 데이터베이스에서 삭제.
                            String id = contact.getId();
                            db.collection("Contact")
                                    .document(id)
                                    .delete()       // 함수이름 delete
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(context,"삭제되었습니다.",Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {

                                        }
                                    });
                            ((MainActivity)context).recreate();

                        }
                    });
                    finishAlert.setNegativeButton("No", null);
                    finishAlert.setCancelable(false);
                    finishAlert.show();

                }
            });

        }
    }
}
