package com.example.raghav.theguide.listing

import android.graphics.Rect
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.raghav.theguide.R
import com.example.raghav.theguide.models.Movie
import kotlinx.android.synthetic.main.fragment_listing.listing
import org.koin.android.ext.android.inject
import kotlinx.android.synthetic.main.fragment_listing.progress_bar as progressBar

/**
 * @author raghav
 */

class ListingFragment : Fragment(), ListingView {
    val presenter: ListingPresenter by inject()
    private val SPAN_COUNT = 2
    private lateinit var moviesList: MutableList<Movie>
    private val movieList by lazy { listing }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_listing, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.setView(this)
    }

    override fun setMovies(moviesList: List<Movie>?) {
        moviesList?.let {
            val insertedPosition = moviesList.size
            this.moviesList.addAll(it)
            movieList.adapter.notifyItemInserted(insertedPosition)
        }
    }

    override fun initListingView() {
        moviesList = ArrayList(0)
        val layoutManager = GridLayoutManager(context, SPAN_COUNT)
        layoutManager.isAutoMeasureEnabled = true
        movieList.layoutManager = layoutManager
        movieList.setHasFixedSize(true)
        movieList.adapter = ListingAdapter(moviesList)
        listing.addOnScrollListener(scroller)
        listing.addItemDecoration(SpaceItemDecoration(2))
    }

    private var scroller = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (dy > 0) {
                val visibleItemCount = recyclerView?.layoutManager?.childCount
                val totalItemCount = recyclerView?.layoutManager?.itemCount
                val pastVisibleItems = (recyclerView?.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                presenter.onScrollDown(visibleItemCount!!, pastVisibleItems, totalItemCount!!)
            } else {
                // do nothing
            }
        }
    }

    class SpaceItemDecoration(var space: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect?.left = space
            outRect?.right = space
            outRect?.top = space
            outRect?.bottom = space
        }
    }
}

