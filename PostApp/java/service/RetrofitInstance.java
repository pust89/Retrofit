package com.pustovit.postapp.service;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Pustovit Vladimir on 06.01.2020.
 * vovapust1989@gmail.com
 */

public class RetrofitInstance {

    private static Retrofit retrofit;
    private final static String BASE_URL = "https://jsonplaceholder.typicode.com/";

    public static PostAppService getService(){

        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(PostAppService.class);
    }
}
