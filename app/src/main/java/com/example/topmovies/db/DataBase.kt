package com.example.topmovies.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieEntity::class], exportSchema = false, version = 1)
abstract class DataBase : RoomDatabase() {

    abstract fun getMovieDao() : MovieDao

}