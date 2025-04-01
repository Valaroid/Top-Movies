package com.example.topmovies.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.topmovies.db.DataBase
import com.example.topmovies.db.MovieDao
import com.example.topmovies.db.MovieEntity
import com.example.topmovies.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun provideDao(@ApplicationContext context: Context) : MovieDao= Room.databaseBuilder(context,DataBase::class.java,Constants.NAME_DATABASE)
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()
        .getMovieDao()


    @Provides
    fun provideMovieEntity()=MovieEntity()

}