package com.example.raghav.theguide.listing

import com.example.raghav.theguide.models.MovieDataModel
import io.reactivex.Observable

/**
 * @author raghav
 */
interface ListingInteractor {
    fun getMovies(pageNumber: Int): Observable<MovieDataModel>
}