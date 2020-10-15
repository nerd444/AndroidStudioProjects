package com.test.nearbysearch.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.test.nearbysearch.MapsActivity;
import com.test.nearbysearch.R;
import com.test.nearbysearch.model.Store;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    Context context;
    ArrayList<Store> storeArrayList;

    public RecyclerViewAdapter(Context context, ArrayList<Store> storeArrayList) {
        this.context = context;
        this.storeArrayList = storeArrayList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Store store = storeArrayList.get(position);
        String name = store.getName();
        String vicinity = store.getVicinity();

        holder.txtName.setText(name);
        holder.txtVicinity.setText(vicinity);
    }

    @Override
    public int getItemCount() {
        return storeArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName;
        public TextView txtVicinity;
        public CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtVicinity = itemView.findViewById(R.id.txtVicinity);
            cardView = itemView.findViewById(R.id.cardView);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Store store = storeArrayList.get(getAdapterPosition());
                    Intent i = new Intent(context, MapsActivity.class);
                    i.putExtra("store", store);
                    i.putExtra("boolean",true);
                    context.startActivity(i);
                }
            });
        }
    }
}
