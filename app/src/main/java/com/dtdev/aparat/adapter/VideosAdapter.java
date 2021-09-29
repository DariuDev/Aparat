package com.dtdev.aparat.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.dtdev.aparat.R;
import com.dtdev.aparat.activites.VideoPlayerActivity;
import com.dtdev.aparat.models.Video;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.VideoViewHolder> {
    Context context;
    List<Video> videoList;
    LayoutInflater layoutInflater;

    public VideosAdapter(Context context, List<Video> videoList) {
        this.context = context;
        this.videoList = videoList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @NotNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.video_row, null);

        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull VideosAdapter.VideoViewHolder holder, int position) {

        Video video = videoList.get(position);
        holder.textView.setText(video.getTitle());
        Picasso.get().load(video.getIcon()).into(holder.imageView);
        holder.cardViewVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , VideoPlayerActivity.class);
                intent.putExtra("video" , video);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    class VideoViewHolder extends RecyclerView.ViewHolder {
        AppCompatImageView imageView;
        AppCompatTextView textView;
        CardView cardViewVideo;

        public VideoViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_video);
            textView = itemView.findViewById(R.id.txv_title);
            cardViewVideo = itemView.findViewById(R.id.card_video);
        }
    }
}

