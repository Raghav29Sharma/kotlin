package com.example.raghav.theguide.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * @author raghav
 */
@Parcelize
data class Movie(
        private var id: String?,
        private var overview: String?,
        @SerializedName("release_date") val releaseDate: String?,
        @SerializedName("poster_path") val posterPath: String?,
        @SerializedName("backdrop_path") private var backdropPath: String?,
        var title: String?,
        @SerializedName("vote_average") private var voteAverage: Double = 0.toDouble()) : Parcelable