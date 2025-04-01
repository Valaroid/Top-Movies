package com.example.topmovies.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.topmovies.model.register.BodyRegister
import com.example.topmovies.model.register.ResponseRegister
import com.example.topmovies.repository.RegisterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(val registerRepository: RegisterRepository) :
    ViewModel() {

     val registerUser = MutableLiveData<ResponseRegister>()
     val isLoading = MutableLiveData<Boolean>()

    fun getRegisterUser(body: BodyRegister, error: (String) -> Unit) = viewModelScope.launch {
        isLoading.postValue(true)

        val response = registerRepository.registerUser(body)
        Log.e("errorr",response.body().toString())

        if (response.isSuccessful) {
            registerUser.postValue(response.body())

        } else {
            error("Something was Wrong try again :)")
        }

        isLoading.postValue(false)


    }


}