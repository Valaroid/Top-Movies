package com.example.topmovies.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.topmovies.util.Constants


@Entity(tableName = Constants.TABLE_MOVIE)
data class MovieEntity(
    @PrimaryKey()
    var id : Int=0,

    var title:String="",
    var rateImdb : String="",
    var country : String="",
    var year : String="",
    var image : String=""
)
