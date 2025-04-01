package com.example.topmovies.repository

import com.example.topmovies.net.ApiService
import javax.inject.Inject

class HomeRepository @Inject constructor(val api : ApiService) {

    suspend fun getPosterHome(id:Int)=api.getPosterHome(id)

    suspend fun getGenresList()=api.getGenresList()

    suspend fun getLastMovieList()=api.getLastMovieList()





}