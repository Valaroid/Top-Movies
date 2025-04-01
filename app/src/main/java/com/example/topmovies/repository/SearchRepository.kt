package com.example.topmovies.repository

import com.example.topmovies.net.ApiService
import javax.inject.Inject

class SearchRepository @Inject constructor(val api : ApiService) {

       suspend fun getSearchMovie(name : String)=api.getSearchMovie(name)

}