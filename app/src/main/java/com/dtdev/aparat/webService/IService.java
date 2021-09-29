package com.dtdev.aparat.webService;

import com.dtdev.aparat.models.Category;
import com.dtdev.aparat.models.News;
import com.dtdev.aparat.models.Video;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IService {

    @GET("getBestVideos.php")
    Call<List<Video>> getBestVideos();

    @GET("getNewVideos.php")
    Call<List<Video>> getNewVideos();

    @GET("getSpecial.php")
    Call<List<Video>> getSpecialVideos();

    @GET("getCategory.php")
    Call<List<Category>> getCategory();

    @POST("login.php")
    @FormUrlEncoded
    Call<ResponseBody> login(@Field("username") String user, @Field("password") String pass);

    @POST("getVideosCategory.php")
    @FormUrlEncoded
    Call<List<Video>> getVideosCategory(@Field("catId") String catId);

    @GET("getNews.php")
    Call<List<News>> getNews();

    @POST("register.php")
    @FormUrlEncoded
    Call<ResponseBody> register(@Field("username") String user, @Field("password") String pass);
}
