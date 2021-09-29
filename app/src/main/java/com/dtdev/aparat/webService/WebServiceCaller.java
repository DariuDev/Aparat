package com.dtdev.aparat.webService;

import com.dtdev.aparat.models.Category;
import com.dtdev.aparat.models.IResponseListener;
import com.dtdev.aparat.models.News;
import com.dtdev.aparat.models.Video;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WebServiceCaller {

    IService iService;

    public WebServiceCaller() {
        iService = ApiClient.getClient().create(IService.class);
    }

    public void getNewVideos(IResponseListener responseListener) {

        Call<List<Video>> call = iService.getNewVideos();
        call.enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Call<List<Video>> call, Response<List<Video>> response) {

                responseListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Video>> call, Throwable t) {
                responseListener.onFailure(t.getMessage().toString());
            }
        });
    }


    public  void getCategoryVideos( String catId ,  IResponseListener responseListener) {

        Call<List<Video>> call = iService.getVideosCategory(catId);
        call.enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Call<List<Video>> call, Response<List<Video>> response) {

               // String res = response.body().toString();



                    responseListener.onSuccess(response.body());

            }

            @Override
            public void onFailure(Call<List<Video>> call, Throwable t) {
                responseListener.onFailure(t.getMessage().toString());
            }

        });

    }






    public void getBestVideos(IResponseListener responseListener) {

        Call<List<Video>> call = iService.getBestVideos();
        call.enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Call<List<Video>> call , Response<List<Video>> response) {

                responseListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Video>> call, Throwable t) {
                responseListener.onFailure(t.getMessage().toString());
            }
        });
    }

    public void getSpecialVideos(IResponseListener responseListener) {

        Call<List<Video>> call = iService.getSpecialVideos();
        call.enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Call<List<Video>> call, Response<List<Video>> response) {

                responseListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Video>> call, Throwable t) {
                responseListener.onFailure(t.getMessage().toString());
            }
        });
    }

    public void getCategories(IResponseListener responseListener) {
        Call<List<Category>> call = iService.getCategory();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                responseListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                responseListener.onFailure(t.getMessage().toString());
            }
        });
    }

    public void getNews(IResponseListener responseListener) {
        Call<List<News>> call = iService.getNews();
        call.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                responseListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                responseListener.onFailure(t.getMessage().toString());
            }
        });
    }
}
