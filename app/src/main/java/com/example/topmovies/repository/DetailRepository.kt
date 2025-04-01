package com.example.topmovies.repository

import com.example.topmovies.db.MovieDao
import com.example.topmovies.db.MovieEntity
import com.example.topmovies.net.ApiService
import javax.inject.Inject


class DetailRepository @Inject constructor(val api : ApiService , val movieDao: MovieDao) {

    suspend fun getMovieDetail(id:Int)=api.getMovieDetail(id)

    suspend fun isExistMovie(movieId : Int)= movieDao.isExistMovie(movieId)

    suspend fun deleteMovieFromDb(movie : MovieEntity)=movieDao.removeMovie(movie)

    suspend fun insertMovieToDb(movie : MovieEntity)=movieDao.insertMovie(movie)

}