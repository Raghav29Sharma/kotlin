package com.example.raghav.theguide.listing

/**
 * @author raghav
 */
interface ListingPresenter {
    fun setView(view: ListingView)
    fun onDestroy()
    fun onScrollDown(visibleItemCount: Int, pastVisibleItems: Int, totalItemCount: Int)
}