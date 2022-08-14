package com.hugoandrada.movieapp.data.remote

import com.hugoandrada.movieapp.data.model.MovieList
import com.hugoandrada.movieapp.repository.WebService
import com.hugoandrada.movieapp.utils.AppConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DataSourceImplement(private val webService: WebService) : MovieDataSource {

    override suspend fun getMovies(): MovieList = withContext(Dispatchers.IO) {
        webService.getMovies(AppConstants.API_KEY,AppConstants.LANGUAGE)
    }
}