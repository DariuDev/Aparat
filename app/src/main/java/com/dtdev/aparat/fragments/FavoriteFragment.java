package com.dtdev.aparat.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dtdev.aparat.R;
import com.dtdev.aparat.adapter.VideosAdapter;
import com.dtdev.aparat.database.AppDatabase;
import com.dtdev.aparat.databinding.FragmentFavoriteBinding;

public class FavoriteFragment extends Fragment {

    FragmentFavoriteBinding fragmentFavoriteBinding;
    AppDatabase appDatabase;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentFavoriteBinding = FragmentFavoriteBinding.inflate(getLayoutInflater());
        appDatabase = AppDatabase.getInstance(getActivity());

        return fragmentFavoriteBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        fragmentFavoriteBinding.recyclerFavorite.setAdapter(new VideosAdapter(getActivity(),
                appDatabase.idao().getAllVideos()));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity() , 2);
        fragmentFavoriteBinding.recyclerFavorite.setLayoutManager(gridLayoutManager);


    }
}