package com.hugoandrada.movieapp.repo.remote

import com.hugoandrada.movieapp.data.local.MovieDao
import com.hugoandrada.movieapp.data.local.toMovieEntity
import com.hugoandrada.movieapp.data.local.toMovieList
import com.hugoandrada.movieapp.data.model.MovieList
import com.hugoandrada.movieapp.data.remote.DataSourceImplement
import com.hugoandrada.movieapp.utils.InternetCheck
import javax.inject.Inject

class RepoImplement
@Inject
constructor(
    private val dataSource: DataSourceImplement,
    private val dao: MovieDao,
) : MovieRepository {

    override suspend fun getMovies(): MovieList {

        return if (InternetCheck.isNetworkAvailable()) {
            dataSource.getMovies().results.forEach { movie ->
                dao.deleteMovieRoom(movie.toMovieEntity())
                dao.insertMovieRoom(movie.toMovieEntity())
            }
            dao.getMovieRoom().toMovieList()
        } else {
            dao.getMovieRoom().toMovieList()
        }
    }
}