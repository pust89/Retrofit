package com.pustovit.tmdbclient.service;

import com.pustovit.tmdbclient.model.MovieDBResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Pustovit Vladimir on 07.01.2020.
 * vovapust1989@gmail.com
 */

public interface MovieDataService {

    @GET("movie/popular")
    Call<MovieDBResponse> getPopularMovies(@Query("api_key")String apiKey);

}
