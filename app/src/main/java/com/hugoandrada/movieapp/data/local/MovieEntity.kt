package com.hugoandrada.movieapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hugoandrada.movieapp.data.model.Movie
import com.hugoandrada.movieapp.data.model.MovieList

@Entity(tableName = "favoritos")
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "titulo")
    val title: String,
    @ColumnInfo(name = "poster")
    val poster_path: String,
    @ColumnInfo(name = "cover")
    val backdrop_path: String,
    @ColumnInfo(name = "popularidad")
    val popularity: Float,
    @ColumnInfo(name = "votos")
    val vote_average: Float,
    @ColumnInfo(name = "descripcion")
    val overview: String
)

fun MovieEntity.toMovie(): Movie = Movie(
    id = id,
    title = title,
    poster_path = poster_path,
    backdrop_path = backdrop_path,
    popularity = popularity,
    vote_average = vote_average,
    overview = overview
)

fun List<MovieEntity>.toMovieList(): MovieList {
    val results = mutableListOf<Movie>()
    this.map { results.add(it.toMovie()) }
    return MovieList(results)
}
