package com.hugoandrada.movieapp.repo.api

import com.hugoandrada.movieapp.data.model.MovieList
import com.hugoandrada.movieapp.utils.AppConstants
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(AppConstants.END_POINT)
    suspend fun getMovies(
        @Query("api_key") apikey: String,
        @Query("language") language: String
    ): MovieList
}