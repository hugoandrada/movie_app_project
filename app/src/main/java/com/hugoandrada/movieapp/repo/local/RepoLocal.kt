package com.hugoandrada.movieapp.repo.local

import com.hugoandrada.movieapp.data.local.MovieEntity
import com.hugoandrada.movieapp.data.model.MovieList

interface RepoLocal {
    suspend fun getFavoritos() : MovieList
    suspend fun saveFavoritos(movieEntity: MovieEntity)
    suspend fun deleteFavoritos(movieEntity: MovieEntity)
}