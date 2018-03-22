package com.example.raghav.theguide.listing

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.support.v7.graphics.Palette
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.raghav.theguide.BASE_POSTER_PATH
import com.example.raghav.theguide.R
import com.example.raghav.theguide.inflate
import com.example.raghav.theguide.models.Movie
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.listitem_listing.view.movie_image as movieImage
import kotlinx.android.synthetic.main.listitem_listing.view.movie_name as movieName

/**
 * @author raghav
 */

class ListingAdapter(private val list: List<Movie>?) : RecyclerView.Adapter<ListingAdapter.ListingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListingViewHolder {
        val view = parent.inflate(R.layout.listitem_listing)
        return ListingViewHolder(view)
    }

    override fun getItemCount(): Int = list?.size ?: 0

    override fun onBindViewHolder(holder: ListingViewHolder, position: Int) {
        holder.bind(list?.get(position))
    }

    class ListingViewHolder(root: View?) : RecyclerView.ViewHolder(root) {
        fun bind(movie: Movie?) = with(itemView) {
            val imageURL = StringBuilder(2).append(BASE_POSTER_PATH).append(movie?.posterPath).toString()
            movieName.text = movie?.title
            Picasso.with(context).load(imageURL).fit().centerCrop().into(movieImage, object : Callback {
                override fun onSuccess() {
                    val bitmap = (movieImage.drawable as BitmapDrawable).bitmap
                    bitmap?.let { it: Bitmap ->
                        Palette.from(it).generate({ palette ->
                            run {
                                val textSwatch: Palette.Swatch? = palette.dominantSwatch
                                textSwatch?.let {
                                    movieName.run {
                                        setTextColor(textSwatch.titleTextColor)
                                        setBackgroundColor(textSwatch.rgb)
                                    }
                                }
                            }
                        })
                    }
                }

                override fun onError() {
                    // do nothing
                }
            })
        }
    }
}
