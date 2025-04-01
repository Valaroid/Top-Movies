package com.example.topmovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.topmovies.db.MovieEntity
import com.example.topmovies.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(val favoriteRepository: FavoriteRepository) :
    ViewModel() {

    val myListMovieLive = MutableLiveData<List<MovieEntity>>()
    val isEmpty = MutableLiveData<Boolean>()


    fun getListMovie() = viewModelScope.launch {

        val result = favoriteRepository.getListMovie()

        if (result.isNotEmpty()) {
            isEmpty.postValue(false)
            myListMovieLive.postValue(result)

        } else {

            isEmpty.postValue(true)
        }


    }


}