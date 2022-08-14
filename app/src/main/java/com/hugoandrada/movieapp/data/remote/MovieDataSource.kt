package com.hugoandrada.movieapp.data.remote

import com.hugoandrada.movieapp.data.model.MovieList

interface MovieDataSource {
    suspend fun getMovies(): MovieList
}