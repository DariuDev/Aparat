package com.dtdev.aparat.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dtdev.aparat.R;
import com.dtdev.aparat.adapter.CategoryAdapter;
import com.dtdev.aparat.databinding.FragmentCategoryBinding;
import com.dtdev.aparat.models.Category;
import com.dtdev.aparat.models.IResponseListener;
import com.dtdev.aparat.webService.WebServiceCaller;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;


public class CategoryFragment extends Fragment {

    FragmentCategoryBinding fragmentCategoryBinding;
    WebServiceCaller webServiceCaller;

    public CategoryFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentCategoryBinding = FragmentCategoryBinding.inflate(getLayoutInflater());
        fragmentCategoryBinding.progressBar.setVisibility(View.VISIBLE);

        webServiceCaller = new WebServiceCaller();
        webServiceCaller.getCategories(new IResponseListener() {
            @Override
            public void onSuccess(Object responseMassage) {
                Log.e("responseMassageC" , ""+responseMassage);
                fragmentCategoryBinding.progressBar.setVisibility(View.GONE);
                fragmentCategoryBinding.recyclerCategory.setAdapter(new CategoryAdapter(getActivity(),
                        (List<Category>) responseMassage));
                fragmentCategoryBinding.recyclerCategory.setLayoutManager(new LinearLayoutManager(getActivity(),
                        RecyclerView.VERTICAL, false));
            }
            @Override
            public void onFailure(String errorResponseMassage) {
                fragmentCategoryBinding.progressBar.setVisibility(View.VISIBLE);
            }

        });

        return fragmentCategoryBinding.getRoot();
    }
}