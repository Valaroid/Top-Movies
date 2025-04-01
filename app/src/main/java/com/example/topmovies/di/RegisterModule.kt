package com.example.topmovies.di

import com.example.topmovies.model.register.BodyRegister
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RegisterModule {

    @Provides
    @Singleton
    fun provideBodyRegister()= BodyRegister()


}