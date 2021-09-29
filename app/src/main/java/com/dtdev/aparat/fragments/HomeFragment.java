package com.dtdev.aparat.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.dtdev.aparat.R;
import com.dtdev.aparat.adapter.NewsAdapter;
import com.dtdev.aparat.adapter.VideosAdapter;
import com.dtdev.aparat.adapter.ViewPagerAdapter;
import com.dtdev.aparat.models.IResponseListener;
import com.dtdev.aparat.models.News;
import com.dtdev.aparat.models.Video;
import com.dtdev.aparat.webService.WebServiceCaller;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;


public class HomeFragment extends Fragment implements RecyclerView.OnItemTouchListener {

    WebServiceCaller webServiceCaller;
    RecyclerView recyclerViewNewVideos, recyclerViewSpecialVideos, recycler_best_videos;
    ProgressBar progressNewVideo, progressSpecialVideo, progressBestVideo;
    ViewPager viewPager;
    private float mLastX = 0, mLastY = 0;
    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerViewNewVideos = view.findViewById(R.id.recycler_new_videos);
        recyclerViewSpecialVideos = view.findViewById(R.id.recycler_special_videos);
        recycler_best_videos = view.findViewById(R.id.recycler_best_videos);
        viewPager = view.findViewById(R.id.view_pager);

        progressBestVideo = view.findViewById(R.id.progress_best);
        progressSpecialVideo = view.findViewById(R.id.progress_special);
        progressNewVideo = view.findViewById(R.id.progress_new);

        webServiceCaller = new WebServiceCaller();

        progressNewVideo.setVisibility(View.VISIBLE);

        webServiceCaller.getNewVideos(new IResponseListener() {
            @Override
            public void onSuccess(Object responseMassage) {
                progressNewVideo.setVisibility(View.GONE);
                Log.e("responseMassageNew" , ""+responseMassage);
                recyclerViewNewVideos.setAdapter(new VideosAdapter(getActivity()
                        , (List<Video>) responseMassage));
                recyclerViewNewVideos.setLayoutManager(new LinearLayoutManager(getActivity()
                        , RecyclerView.HORIZONTAL, false));
                recyclerViewNewVideos.addOnItemTouchListener(HomeFragment.this);
            }

            @Override
            public void onFailure(String errorResponseMassage) {
                progressNewVideo.setVisibility(View.GONE);
            }

        });
        progressSpecialVideo.setVisibility(View.VISIBLE);
        webServiceCaller.getSpecialVideos(new IResponseListener() {
            @Override
            public void onSuccess(Object responseMassage) {
                Log.e("responseMassageSpecial" , ""+responseMassage);
                progressSpecialVideo.setVisibility(View.GONE);
                recyclerViewSpecialVideos.setAdapter(new VideosAdapter(getActivity()
                        , (List<Video>) responseMassage));

                recyclerViewSpecialVideos.setLayoutManager(new LinearLayoutManager(getActivity()
                        , RecyclerView.HORIZONTAL, false));

                recyclerViewSpecialVideos.addOnItemTouchListener(HomeFragment.this);
            }

            @Override
            public void onFailure(String errorResponseMassage) {
                progressSpecialVideo.setVisibility(View.GONE);
            }
        });
        progressBestVideo.setVisibility(View.VISIBLE);
        webServiceCaller.getBestVideos(new IResponseListener() {
            @Override
            public void onSuccess(Object responseMassage) {
                progressBestVideo.setVisibility(View.GONE);
                Log.e("responseMassageBest" , ""+responseMassage);
                recycler_best_videos.setAdapter(new VideosAdapter(getActivity()
                        , (List<Video>) responseMassage));

                recycler_best_videos.setLayoutManager(new LinearLayoutManager(getActivity()
                        , RecyclerView.HORIZONTAL, false));
                recycler_best_videos.addOnItemTouchListener(HomeFragment.this);
            }

            @Override
            public void onFailure(String errorResponseMassage) {
                progressBestVideo.setVisibility(View.GONE);
            }

        });
        webServiceCaller.getNews(new IResponseListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public void onSuccess(Object responseMassage) {
                viewPager.setAdapter(new NewsAdapter(getActivity(), (List<News>) responseMassage));
                viewPager.setOnTouchListener(new View.OnTouchListener() {

                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_MOVE:
                                float deltaX = event.getX() - mLastX;
                                float deltaY = event.getY() - mLastY;
                                if (Math.abs(deltaX) > 20 && Math.abs(deltaX) > Math.abs(deltaY)) {
                                    v.getParent().requestDisallowInterceptTouchEvent(true);
                                }
                                mLastX = event.getX();
                                mLastY = event.getY();
                                break;
                            case MotionEvent.ACTION_CANCEL:
                            case MotionEvent.ACTION_UP:
                                v.getParent().requestDisallowInterceptTouchEvent(false);
                                break;
                        }
                        return false;
                    }
                });
            }

            @Override
            public void onFailure(String errorResponseMassage) {

            }

        });

        return view;
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull @NotNull RecyclerView rv, @NonNull @NotNull MotionEvent e) {
        int action = e.getAction();

        switch (action) {
            case MotionEvent.ACTION_MOVE:
                float deltaX = e.getX() - mLastX;
                float deltaY = e.getY() - mLastY;
                if (Math.abs(deltaX) > 20 && Math.abs(deltaX) > Math.abs(deltaY)) {
                    rv.getParent().requestDisallowInterceptTouchEvent(true);
                }
                mLastX = e.getX();
                mLastY = e.getY();
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                rv.getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull @NotNull RecyclerView rv, @NonNull @NotNull MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}