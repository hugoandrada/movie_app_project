package com.hugoandrada.movieapp.data.local

import androidx.room.*

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieRoom(movieEntity: MovieEntity)

    @Query("SELECT * FROM favoritos")
    suspend fun getMovieRoom() : List<MovieEntity>

    @Delete
    suspend fun deleteMovieRoom(movieEntity: MovieEntity)
}