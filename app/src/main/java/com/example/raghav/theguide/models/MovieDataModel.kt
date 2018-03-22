package com.example.raghav.theguide.models

import com.google.gson.annotations.SerializedName

/**
 * @author raghav
 */
data class MovieDataModel(
        @SerializedName("results") val moviesList: List<Movie>,
        @SerializedName("total_pages") val totalPages: Int
)