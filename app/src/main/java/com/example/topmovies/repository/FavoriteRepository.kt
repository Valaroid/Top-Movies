package com.example.topmovies.repository

import com.example.topmovies.db.MovieDao
import com.example.topmovies.db.MovieEntity
import javax.inject.Inject


class FavoriteRepository@Inject constructor(val dao :MovieDao) {

    suspend fun getListMovie(): List<MovieEntity> =dao.getListMovie()


}