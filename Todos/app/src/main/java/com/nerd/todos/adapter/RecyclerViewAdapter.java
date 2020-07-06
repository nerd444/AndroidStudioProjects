package com.nerd.todos.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nerd.todos.R;
import com.nerd.todos.model.Todos;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Context context;
    ArrayList<Todos> todosArrayList;

    public RecyclerViewAdapter(Context context, ArrayList<Todos> todosArrayList) {
        this.context = context;
        this.todosArrayList = todosArrayList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Todos todos = todosArrayList.get(position);
        int userId = todos.getUserId();
        String title = todos.getTitle();
        boolean completed = todos.isCompleted();

        if (completed == false){
            holder.txtCompleted.setText("completed : "+"실패");
            holder.img.setImageResource(R.drawable.falsejjj);
        }else if (completed == true){
            holder.txtCompleted.setText("completed : "+"완료");
            holder.img.setImageResource(R.drawable.truej);
        }

        holder.txtTitle.setText(title);
        holder.txtUserId.setText("userId : "+userId);

    }

    @Override
    public int getItemCount() {
        return todosArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTitle;
        public TextView txtUserId;
        public TextView txtCompleted;
        public ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtUserId = itemView.findViewById(R.id.txtUserId);
            txtCompleted = itemView.findViewById(R.id.txtCompleted);
            img = itemView.findViewById(R.id.img);
        }
    }
}
