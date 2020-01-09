package com.pustovit.tmdbclient.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import com.pustovit.tmdbclient.R;
import com.pustovit.tmdbclient.adapter.MovieAdapter;
import com.pustovit.tmdbclient.model.Movie;
import com.pustovit.tmdbclient.model.MovieDBResponse;
import com.pustovit.tmdbclient.service.MovieDataService;
import com.pustovit.tmdbclient.service.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "myTag";

    private List<Movie> movies;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rvMovies);


        mSwipeRefreshLayout = findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPopularMovies();
            }
        });
        getSupportActionBar().setTitle("TMDB Popular movies today ");

        getPopularMovies();

    }


    private void getPopularMovies() {
        MovieDataService movieDataService = RetrofitInstance.getService();

        Call<MovieDBResponse> movieDBResponseCall = movieDataService.getPopularMovies(getString(R.string.api_key));

        movieDBResponseCall.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {

                MovieDBResponse movieDBResponse = response.body();

                if (movieDBResponse != null && movieDBResponse.getMovies() != null) {
                    movies = movieDBResponse.getMovies();
                    showOnRecyclerView();
                }

                Log.d(TAG, "onResponse: " + movieDBResponse.getMovies().size());
            }

            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {

            }
        });

    }

    private void showOnRecyclerView() {
        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        } else{
            recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setItemViewCacheSize(20);
        movieAdapter = new MovieAdapter(this, movies);
        recyclerView.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();


        mSwipeRefreshLayout.setRefreshing(false);
    }


}
