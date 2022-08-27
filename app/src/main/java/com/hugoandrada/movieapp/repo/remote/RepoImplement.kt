package com.hugoandrada.movieapp.repo.remote

import com.hugoandrada.movieapp.data.model.MovieList
import com.hugoandrada.movieapp.data.remote.DataSourceImplement
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepoImplement
@Inject
constructor(private val dataSource: DataSourceImplement) : MovieRepository {
    override suspend fun getMovies(): MovieList =
        withContext(Dispatchers.IO) {
            dataSource.getMovies()
        }
}