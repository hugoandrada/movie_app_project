package com.hugoandrada.movieapp.repository.repo

import com.hugoandrada.movieapp.data.model.MovieList

interface MovieRepository {
    suspend fun getMovies() : MovieList
}