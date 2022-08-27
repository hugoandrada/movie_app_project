package com.hugoandrada.movieapp.data.remote

import com.hugoandrada.movieapp.data.model.MovieList
import com.hugoandrada.movieapp.repository.api.ApiService
import com.hugoandrada.movieapp.utils.AppConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DataSourceImplement
@Inject constructor(private val webService: ApiService) : MovieDataSource {
    override suspend fun getMovies(): MovieList =
        withContext(Dispatchers.IO) {
            webService.getMovies(AppConstants.API_KEY, AppConstants.LANGUAGE)
        }
}