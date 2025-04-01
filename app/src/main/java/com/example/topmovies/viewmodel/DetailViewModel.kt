package com.example.topmovies.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.topmovies.db.MovieEntity
import com.example.topmovies.model.detail.ResponseDetail
import com.example.topmovies.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(val detailRepository: DetailRepository): ViewModel() {

    val thisMovieDataLive = MutableLiveData<ResponseDetail>()

    val isExistInDb=MutableLiveData<Boolean>()

    val isLoading = MutableLiveData<Boolean>()


    fun getMovieDetail(movieId:Int)=viewModelScope.launch {
        isLoading.postValue(true)

        val response = detailRepository.getMovieDetail(movieId)
        if (response.isSuccessful){
            thisMovieDataLive.postValue(response.body())
            Log.e("ress",response.body().toString())
        }

        isLoading.postValue(false)
    }


   fun checkIsExistMovieInDb(movieId:Int)=viewModelScope.launch {

       val result = detailRepository.isExistMovie(movieId)

       if (result){
           isExistInDb.postValue(true)

       }else{
           isExistInDb.postValue(false)
       }


   }

    fun doWorkOnDb(movieEntity: MovieEntity)=viewModelScope.launch {


        if (isExistInDb.value!!){

            detailRepository.deleteMovieFromDb(movieEntity)

        }else{

            detailRepository.insertMovieToDb(movieEntity)
        }



    }













}