package com.example.topmovies.model.detail

import com.google.gson.annotations.SerializedName

data class ResponseDetail(
    @SerializedName("actors")
    val actors: String?,
    @SerializedName("awards")
    val awards: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("director")
    val director: String?,
    @SerializedName("genres")
    val genres: List<String?>?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("images")
    val images: List<String?>?,
    @SerializedName("imdb_id")
    val imdb_id: String?,
    @SerializedName("imdb_rating")
    val imdb_rating: String?,
    @SerializedName("imdb_votes")
    val imdb_votes: String?,
    @SerializedName("metascore")
    val metascore: String?,
    @SerializedName("plot")
    val plot: String?,
    @SerializedName("poster")
    val poster: String?,
    @SerializedName("rated")
    val rated: String?,
    @SerializedName("released")
    val released: String?,
    @SerializedName("runtime")
    val runtime: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("writer")
    val writer: String?,
    @SerializedName("year")
    val year: String?
)