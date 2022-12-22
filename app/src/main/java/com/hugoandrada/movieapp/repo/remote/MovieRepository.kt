package com.hugoandrada.movieapp.repo.remote

import com.hugoandrada.movieapp.data.model.Movie
import com.hugoandrada.movieapp.data.model.MovieList

interface MovieRepository {
    suspend fun getMovies() : MovieList
}