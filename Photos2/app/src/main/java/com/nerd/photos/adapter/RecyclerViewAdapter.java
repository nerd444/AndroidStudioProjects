package com.nerd.photos.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.nerd.photos.R;
import com.nerd.photos.Second;
import com.nerd.photos.model.Photos;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    Context context;
    ArrayList<Photos> photosArrayList;

    public RecyclerViewAdapter(Context context, ArrayList<Photos> photosArrayList) {
        this.context = context;
        this.photosArrayList = photosArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photos_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Photos photos = photosArrayList.get(position);
        String title = photos.getTitle();
        int id = photos.getId();
        int albumId = photos.getAlbumId();
        String thumbnailUrl = photos.getThumbnailUrl();

        holder.txtTitle.setText(title);
        holder.txtId.setText("userId : "+id);
        holder.txtAlbumId.setText("albumId : "+albumId);

        GlideUrl glideUrl = new GlideUrl(thumbnailUrl, new LazyHeaders.Builder().addHeader("User-Agent","Your-user-agent").build());
        Glide.with(context).load(glideUrl).centerCrop().placeholder(R.drawable.ic_launcher_foreground).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return photosArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTitle;
        public TextView txtId;
        public TextView txtAlbumId;
        public ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtId = itemView.findViewById(R.id.txtid);
            txtAlbumId = itemView.findViewById(R.id.txtAlbumId);
            img = itemView.findViewById(R.id.img);

            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Photos photos = photosArrayList.get(getAdapterPosition());
                    Intent i = new Intent(context, Second.class);
                    i.putExtra("img",photos);
                    context.startActivity(i);
                }
            });
        }
    }
}
