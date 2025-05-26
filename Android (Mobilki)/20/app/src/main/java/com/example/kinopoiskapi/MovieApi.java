package com.example.kinopoiskapi;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface MovieApi {
    @Headers({
            "X-API-KEY: a79189a4-40b9-403b-8306-764f45fbd6d1",
            "Accept: application/json"
    })
    @GET("api/v2.1/films/search-by-keyword")
    Call<MovieSearchResult> searchMovies(
            @Query("keyword") String keyword,
            @Query("page") int page
    );


    class MovieSearchResult {
        public List<MovieResponse> films;
    }
}

