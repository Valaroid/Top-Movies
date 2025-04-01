package com.example.topmovies.net

import com.example.topmovies.model.detail.ResponseDetail
import com.example.topmovies.model.home.ResponseGenresList
import com.example.topmovies.model.home.ResponseLastMovieHome
import com.example.topmovies.model.register.BodyRegister
import com.example.topmovies.model.register.ResponseRegister
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @POST("register")
    suspend fun registerUser(@Body data : BodyRegister) : Response<ResponseRegister>

    @GET("genres/{genre_id}/movies")
    suspend fun getPosterHome(@Path ("genre_id") id:Int ) : Response<ResponseLastMovieHome>

    @GET("genres")
    suspend fun getGenresList() :Response<ResponseGenresList>

    @GET("movies")
    suspend fun getLastMovieList() :Response<ResponseLastMovieHome>


    @GET("movies")
    suspend fun getSearchMovie(@Query("q") nameMovie : String ) :Response<ResponseLastMovieHome>

    @GET("movies/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") id:Int):Response<ResponseDetail>




}