package com.hugoandrada.movieapp.data.model

data class Movie(
    val id: Int = -1,
    val title: String,
    val poster_path: String,
    val backdrop_path: String,
    val popularity: Float,
    val vote_average: Float,
    val overview: String
)

data class MovieList(
    val results: MutableList<Movie> = mutableListOf()
)
