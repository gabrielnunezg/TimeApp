package com.sng.hellowworld;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PlaceholderApi {
    @GET("posts")
    Call<List<Post>> getPosts();
}