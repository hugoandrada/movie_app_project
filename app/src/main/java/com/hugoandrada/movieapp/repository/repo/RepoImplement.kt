package com.hugoandrada.movieapp.repository.repo

import com.hugoandrada.movieapp.data.model.MovieList
import com.hugoandrada.movieapp.data.remote.DataSourceImplement
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepoImplement(private val dataSource: DataSourceImplement) : MovieRepository {

    override suspend fun getMovies(): MovieList = withContext(Dispatchers.IO) {
        dataSource.getMovies()
    }
}