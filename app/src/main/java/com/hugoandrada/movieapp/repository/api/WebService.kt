package com.hugoandrada.movieapp.repository

import com.google.gson.GsonBuilder
import com.hugoandrada.movieapp.data.model.MovieList
import com.hugoandrada.movieapp.utils.AppConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET(AppConstants.END_POINT)
    suspend fun getMovies(
        @Query("api_key") apikey: String,
        @Query("language") language: String
    ): MovieList
}

object RetrofitClient {
    val webservice by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(WebService::class.java)
    }
}