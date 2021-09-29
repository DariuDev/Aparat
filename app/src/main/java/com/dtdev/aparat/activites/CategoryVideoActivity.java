package com.dtdev.aparat.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.dtdev.aparat.adapter.CategoryAdapter;
import com.dtdev.aparat.adapter.VideosAdapter;
import com.dtdev.aparat.databinding.ActivityCategoryVideoBinding;
import com.dtdev.aparat.fragments.HomeFragment;
import com.dtdev.aparat.models.Category;
import com.dtdev.aparat.models.IResponseListener;
import com.dtdev.aparat.models.Video;
import com.dtdev.aparat.webService.WebServiceCaller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;

public class CategoryVideoActivity extends AppCompatActivity {
    ActivityCategoryVideoBinding activityCategoryVideoBinding;
    Bundle bundle;
    Category category;
    WebServiceCaller webServiceCaller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCategoryVideoBinding = ActivityCategoryVideoBinding.inflate(getLayoutInflater());
        setContentView(activityCategoryVideoBinding.getRoot());
        bundle = getIntent().getExtras();
        category = bundle.getParcelable("categoryVideo");
        activityCategoryVideoBinding.txv.setText(category.getTitle());
        activityCategoryVideoBinding.progressBar.setVisibility(View.VISIBLE);
        String catId = category.getId();
        webServiceCaller = new WebServiceCaller();
        webServiceCaller.getCategoryVideos(catId , new IResponseListener() {
            @Override
            public void onSuccess(Object responseMassage) {

                activityCategoryVideoBinding.progressBar.setVisibility(View.GONE);
                Log.e("responseMassageCVA", "" + responseMassage);
                activityCategoryVideoBinding.recyclerCategoryVideo.setAdapter(new VideosAdapter(getApplicationContext()
                        , (List<Video>) responseMassage));
                activityCategoryVideoBinding.recyclerCategoryVideo.setLayoutManager(new LinearLayoutManager(getApplicationContext()
                        , RecyclerView.VERTICAL, false));
            }

            @Override
            public void onFailure(String errorResponseMassage) {
                activityCategoryVideoBinding.progressBar.setVisibility(View.GONE);
            }
        });
    }
}
