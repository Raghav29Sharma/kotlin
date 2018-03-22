package com.example.raghav.theguide.listing

import com.example.raghav.theguide.MOVIES_LIST
import com.example.raghav.theguide.models.MovieDataModel
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import java.net.URL

/**
 * @author raghav
 */
class ListingInteractorImpl : ListingInteractor {
    override fun getMovies(pageNumber: Int): Observable<MovieDataModel> {
        return Observable.fromCallable { get(pageNumber) }
    }

    private fun get(pageNumber: Int): MovieDataModel {
        val url = String.format(MOVIES_LIST, pageNumber)
        val json = URL(url).readText()
        return GsonBuilder().create().fromJson(json, MovieDataModel::class.java)
    }
}