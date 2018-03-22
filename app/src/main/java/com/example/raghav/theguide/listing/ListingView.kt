package com.example.raghav.theguide.listing

import com.example.raghav.theguide.models.Movie

/**
 * @author raghav
 */
interface ListingView {
    fun showProgress()
    fun setMovies(moviesList: List<Movie>?)
    fun initListingView()
    fun hideProgress()
}