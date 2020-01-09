package com.pustovit.postapp.service;

import com.pustovit.postapp.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Pustovit Vladimir on 06.01.2020.
 * vovapust1989@gmail.com
 */

public interface PostAppService {

    @POST("posts")
    Call<User>getResult(@Body User user);
}
