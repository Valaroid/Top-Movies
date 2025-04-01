package com.example.topmovies.repository

import com.example.topmovies.model.register.BodyRegister
import com.example.topmovies.net.ApiService
import javax.inject.Inject

class RegisterRepository @Inject constructor(val api : ApiService) {


    suspend fun registerUser(body : BodyRegister)=api.registerUser(body)






}