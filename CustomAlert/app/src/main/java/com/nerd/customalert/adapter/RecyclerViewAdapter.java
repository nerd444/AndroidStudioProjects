package com.nerd.customalert.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nerd.customalert.R;
import com.nerd.customalert.model.CustomAlert;

import org.json.JSONObject;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;
    ArrayList<CustomAlert> customAlertArrayList;

    public RecyclerViewAdapter(Context context, ArrayList<CustomAlert> customAlertArrayList) {
        this.context = context;
        this.customAlertArrayList = customAlertArrayList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customalert_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        CustomAlert customAlert = customAlertArrayList.get(position);
        String title = customAlert.getTitle();
        String body = customAlert.getBody();

        holder.txtTitle.setText(""+title);
        holder.txtBody.setText(""+body);
    }

    @Override
    public int getItemCount() {
        return customAlertArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtTitle;
        public TextView txtBody;
        public ImageView imgDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtBody = itemView.findViewById(R.id.txtBody);
            imgDelete = itemView.findViewById(R.id.imgDelete);
            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder deleteAlert = new AlertDialog.Builder(context);
                    deleteAlert.setTitle("삭제");
                    deleteAlert.setMessage("정보를 삭제하시겠습니까?");
                    deleteAlert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            customAlertArrayList.remove(getAdapterPosition());
                            notifyDataSetChanged();
                        }
                    });
                    deleteAlert.setNegativeButton("NO", null);
                    deleteAlert.setCancelable(false);
                    deleteAlert.show();
                    return;
                }
            });

        }
    }
}
