package com.example.flixter

import android.content.Context
import android.content.Intent
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
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

@GlideModule
class MyAppGlideModule : AppGlideModule() {
    // Leaving this empty for now
}

const val MOVIE_EXTRA = "MOVIE_EXTRA"
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
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val ivPoster = itemView.findViewById<ImageView>(R.id.ivPoster)
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvOverview = itemView.findViewById<TextView>(R.id.tvOverview)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(movie: Movie) {
            tvTitle.text = movie.title
            tvOverview.text = movie.overview
            Glide.with(context)
                .load(movie.posterImageUrl)
                .placeholder(R.drawable.doge)
                .into(ivPoster)
        }

        override fun onClick(v: View?) {
            // 1. Get notified of the particular movie that's clicked
            val movie = movies[adapterPosition] // Tells us what the position of the particular viewHolder
//            Toast.makeText(context, movie.title, Toast.LENGTH_SHORT).show() {NO LONGER NEEDED SINCE THIS WAS FOR TESTING PURPOSES BUT SHOWS THE TITLE}

            // 2. Use the intent system to navigate to the new activity
            val intent = Intent(context, DetailActivity::class.java) // We want to pass in the movie
//            intent.putExtra("movie_title", movie.title) PASSES IT IN EACH ONE AT A TIME
            intent.putExtra(MOVIE_EXTRA, movie)
            context.startActivity(intent)
        }
    }
}
