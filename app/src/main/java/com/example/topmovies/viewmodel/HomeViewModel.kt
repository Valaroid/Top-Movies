package com.example.topmovies.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.topmovies.model.home.ResponseGenresList
import com.example.topmovies.model.home.ResponseLastMovieHome
import com.example.topmovies.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor( val homeRepository: HomeRepository) : ViewModel() {

    val imgPosterLive = MutableLiveData<ResponseLastMovieHome>()

    val genresListLive = MutableLiveData<ResponseGenresList>()

    val lastMoviesLive = MutableLiveData<ResponseLastMovieHome>()

    val loading = MutableLiveData<Boolean>()



    fun getPosterHome(id:Int)=viewModelScope.launch {

        val response=homeRepository.getPosterHome(id)

        if (response.isSuccessful){
            imgPosterLive.postValue(response.body())
            Log.e("test",response.body().toString())
        }

    }


    fun getGenresList()=viewModelScope.launch {

        val response = homeRepository.getGenresList()
        if (response.isSuccessful){
            genresListLive.postValue(response.body())
        }

    }

    fun getLastMovies()=viewModelScope.launch {
        loading.postValue(true)

        val response = homeRepository.getLastMovieList()

        if (response.isSuccessful){
            lastMoviesLive.postValue(response.body())
        }

        loading.postValue(false)


    }






}