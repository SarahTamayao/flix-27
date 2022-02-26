package com.example.flixter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

@GlideModule
class MyAppGlideModule : AppGlideModule() {
    // Leaving this empty for now
}

private const val TAG = "MovieAdapter"
// Create MovieAdapter class constructor that takes a context and a non-mutable list of movies
class MovieAdapter(private val context: Context, private val movies: List<Movie>)
    : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    // Adapter is an abstract class, so these need to be implemented
    // This is the expensive operation which involves creating a view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i(TAG, "onCreateViewHolder")
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    // Given viewholder and position, take the data at the position and bind it into the viewholder
    // This operation is cheap as you simply bind data to an existing viewholder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i(TAG, "onBindViewholder position $position")
        val movie = movies[position]
        holder.bind(movie)
    }

    // Returns the number of items in our data set
    override fun getItemCount() = movies.size

    // Get references and populate it with the correct data in the movie
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val ivPoster = itemView.findViewById<ImageView>(R.id.ivPoster)
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvOverview = itemView.findViewById<TextView>(R.id.tvOverview)

        fun bind(movie: Movie) {
            tvTitle.text = movie.title
            tvOverview.text = movie.overview
            Glide.with(context)
                .load(movie.posterImageUrl)
                .placeholder(R.drawable.doge)
                .into(ivPoster)
        }
    }
}
