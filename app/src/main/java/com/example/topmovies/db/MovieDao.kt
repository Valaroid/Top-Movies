package com.example.topmovies.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Delete
    suspend fun removeMovie(movie: MovieEntity)

    @Query(" SELECT * FROM table_movie")
    suspend fun getListMovie(): List<MovieEntity>

    @Query("SELECT EXISTS ( SELECT 1 FROM table_movie WHERE id= :movieId) ")
    suspend fun isExistMovie(movieId: Int) : Boolean




}