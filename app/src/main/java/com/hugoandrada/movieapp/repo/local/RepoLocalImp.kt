package com.hugoandrada.movieapp.repo.local

import com.hugoandrada.movieapp.data.local.MovieDao
import com.hugoandrada.movieapp.data.local.MovieEntity
import com.hugoandrada.movieapp.data.local.toMovieList
import com.hugoandrada.movieapp.data.model.MovieList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepoLocalImp
@Inject constructor(private val movieDao: MovieDao) : RepoLocal {

    override suspend fun getFavoritos(): MovieList =
        withContext(Dispatchers.IO) {
            movieDao.getMovieRoom().toMovieList()
        }

    override suspend fun saveFavoritos(movieEntity: MovieEntity) =
        withContext(Dispatchers.IO) {
            movieDao.insertMovieRoom(movieEntity)
        }

    override suspend fun deleteFavoritos(movieEntity: MovieEntity) =
        withContext(Dispatchers.IO) {
            movieDao.deleteMovieRoom(movieEntity)
        }
}