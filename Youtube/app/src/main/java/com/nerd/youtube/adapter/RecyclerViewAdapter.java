package com.nerd.youtube.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.nerd.youtube.Image;
import com.nerd.youtube.R;
import com.nerd.youtube.Video;
import com.nerd.youtube.model.Youtube;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    Context context;
    ArrayList<Youtube> youtubeArrayList;

    public RecyclerViewAdapter(Context context, ArrayList<Youtube> youtubeArrayList) {
        this.context = context;
        this.youtubeArrayList = youtubeArrayList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.youtube_row, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Youtube youtube = youtubeArrayList.get(position);
        String title = youtube.getTitle();
        String description = youtube.getDescription();
        String url = youtube.getUrl();

        holder.txtTitle.setText(title);
        holder.txtDescription.setText(description);

        GlideUrl glideUrl = new GlideUrl(url, new LazyHeaders.Builder().addHeader("User-Agent","Your-user-agent").build());
        Glide.with(context).load(glideUrl).centerCrop().placeholder(R.drawable.ic_launcher_foreground).into(holder.thumbNail);
    }

    @Override
    public int getItemCount() {
        return youtubeArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTitle;
        public TextView txtDescription;
        public ImageView thumbNail;
        public CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            thumbNail = itemView.findViewById(R.id.thumbNail);
            cardView = itemView.findViewById(R.id.cardView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Youtube youtube = youtubeArrayList.get(getAdapterPosition());
                    Intent i = new Intent(context, Video.class);
                    i.putExtra("youtube", youtube);
                    context.startActivity(i);
                }
            });

            thumbNail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Youtube youtube = youtubeArrayList.get(getAdapterPosition());
                    Intent i = new Intent(context, Image.class);
                    i.putExtra("Image", youtube);
                    context.startActivity(i);
                }
            });
        }
    }
}
