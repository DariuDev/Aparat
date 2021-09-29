package com.dtdev.aparat.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.dtdev.aparat.R;
import com.dtdev.aparat.database.AppDatabase;
import com.dtdev.aparat.databinding.ActivityVideoPlayerBinding;
import com.dtdev.aparat.models.Category;
import com.dtdev.aparat.models.Video;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;

public class VideoPlayerActivity extends AppCompatActivity {

    ActivityVideoPlayerBinding activityVideoPlayerBinding;
    Bundle bundle;
    Video video;
    SimpleExoPlayer player;
    AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityVideoPlayerBinding = ActivityVideoPlayerBinding.inflate(getLayoutInflater());
        setContentView(activityVideoPlayerBinding.getRoot());

        bundle = getIntent().getExtras();
        video = bundle.getParcelable("video");
      //  category = bundle.getParcelable("categoryVideo");

        appDatabase = AppDatabase.getInstance(getApplicationContext());

        if (appDatabase.idao().searchVideos(video.getTitle()).size()>0){
            activityVideoPlayerBinding.imgFavorite.setImageResource(R.drawable.ic_baseline_favorite_24);
        }else {
            activityVideoPlayerBinding.imgFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24);
        }



        activityVideoPlayerBinding.txvView.setText(video.getView() + "");
        activityVideoPlayerBinding.txvTime.setText(video.getTime());
        activityVideoPlayerBinding.txvDescription.setText(video.getDescription());


        player = new SimpleExoPlayer.Builder(getApplicationContext()).build();
        MediaItem mediaItem = MediaItem.fromUri(Uri.parse(video.getLink()));
        player.setMediaItem(mediaItem);
        activityVideoPlayerBinding.playerView.setPlayer(player);

        player.play();

        activityVideoPlayerBinding.imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int size = appDatabase.idao().searchVideos(video.getTitle()).size();

                if (size <= 0) {
               long result = appDatabase.idao().insert(video);
                    activityVideoPlayerBinding.imgFavorite.setImageResource(R.drawable.ic_baseline_favorite_24);
                } else {
                    appDatabase.idao().deleteVideo(video.getId());
                    activityVideoPlayerBinding.imgFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                }
            }
        });


    }

    @Override
    protected void onStop() {
        super.onStop();
        if (player != null) {
            player.stop();
        }
    }
}