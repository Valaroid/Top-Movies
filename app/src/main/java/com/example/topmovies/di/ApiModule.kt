package com.example.topmovies.di

import com.example.topmovies.net.ApiService
import com.example.topmovies.util.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideURl()=Constants.URL_BASE

    @Provides
    @Singleton
    fun provideTime()=Constants.TIME_READ

    @Provides
    @Singleton
    fun provideGson() : Gson =GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun provideInterceptor()=HttpLoggingInterceptor().apply {
        level=HttpLoggingInterceptor.Level.BODY
    }


    @Provides
    @Singleton
    fun provideClient(time :Long,interceptor:HttpLoggingInterceptor)=OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .writeTimeout(time,TimeUnit.SECONDS)
        .readTimeout(time,TimeUnit.SECONDS)
        .connectTimeout(time,TimeUnit.SECONDS)
        .build()


    @Provides
    @Singleton
    fun provideApi(client : OkHttpClient,url : String,gson: Gson): ApiService =Retrofit.Builder()
        .baseUrl(url)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(ApiService::class.java)





}