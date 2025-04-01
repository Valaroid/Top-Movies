package com.example.topmovies.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.topmovies.model.home.ResponseLastMovieHome
import com.example.topmovies.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(val searchRepository: SearchRepository) : ViewModel() {

    val searchMoviesListLive = MutableLiveData<ResponseLastMovieHome>()
    val loading = MutableLiveData<Boolean>()
    val isEmpty = MutableLiveData<Boolean>()


    fun getSearchMovie(name: String) = viewModelScope.launch {
        loading.postValue(true)

        val response = searchRepository.getSearchMovie(name)
        if (response.isSuccessful) {
            if (response.body()?.data!!.isNotEmpty()) {
                searchMoviesListLive.postValue(response.body())
                isEmpty.postValue(false)
            } else {
                isEmpty.postValue(true)
            }
        }

        loading.postValue(false)

    }

}