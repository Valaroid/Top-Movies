package com.example.topmovies.model.home

import com.google.gson.annotations.SerializedName

data class ResponseLastMovieHome(
    val `data`: List<Data>,
    val metadata: Metadata?
) {
    data class Data(
        @SerializedName("country")
        val country: String?,
        @SerializedName("genres")
        val genres: List<String?>?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("images")
        val images: List<String?>?,
        @SerializedName("imdb_rating")
        val imdb_rating: String?,
        @SerializedName("poster")
        val poster: String?,
        @SerializedName("title")
        val title: String?,
        @SerializedName("year")
        val year: String?
    )

    data class Metadata(
        @SerializedName("current_page")
        val current_page: String?,
        @SerializedName("page_count")
        val page_count: Int?,
        @SerializedName("per_page")
        val per_page: Int?,
        @SerializedName("total_count")
        val total_count: Int?
    )
}