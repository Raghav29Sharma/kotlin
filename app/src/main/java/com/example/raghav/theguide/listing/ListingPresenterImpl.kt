package com.example.raghav.theguide.listing

import com.example.raghav.theguide.models.MovieDataModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author raghav
 */
class ListingPresenterImpl(private val interactor: ListingInteractor) : ListingPresenter {
    var pageNumber = 1
    var totalPages: Int = 0
    private var view: ListingView? = null

    override fun onDestroy() {
        view = null
    }

    override fun setView(view: ListingView) {
        this.view = view
        this.view?.showProgress()
        this.view?.initListingView()
        getMovies(pageNumber++)
    }

    private fun getMovies(pageNumber: Int) {
        interactor.getMovies(pageNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { movieDataModel -> onMoviesSuccess(movieDataModel) }
    }

    private fun onMoviesSuccess(movieDataModel: MovieDataModel?) {
        view?.hideProgress()
        val areMoviesNotEmpty: Boolean = (movieDataModel?.moviesList?.isNotEmpty() ?: false)
        totalPages = movieDataModel?.totalPages ?: 0
        if (isViewAttached() and areMoviesNotEmpty) {
            view?.setMovies(movieDataModel?.moviesList)
        }
    }

    override fun onScrollDown(visibleItemCount: Int, pastVisibleItems: Int, totalItemCount: Int) {
        if (visibleItemCount + pastVisibleItems >= totalItemCount && pageNumber <= totalPages) {
            getMovies(pageNumber++)
        } else {
            // do nothing
        }
    }

    private fun isViewAttached() = view != null
}
