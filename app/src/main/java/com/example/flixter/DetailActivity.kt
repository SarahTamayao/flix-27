package com.example.flixter

import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RatingBar
import android.widget.TextView

private const val TAG = "DetailActivity"
class DetailActivity : AppCompatActivity() {

    // These declare lateinit variables for the screen components
    private lateinit var tvTitle: TextView
    private lateinit var tvOverview: TextView
    private lateinit var ratingBar: RatingBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        // Find views by id
        tvTitle = findViewById(R.id.tvTitle)
        tvOverview = findViewById(R.id.tvOverview)
        ratingBar = findViewById(R.id.rbVoteAverage)

        val movie = intent.getParcelableExtra<Movie>(MOVIE_EXTRA) as Movie
        Log.i(TAG, "Movie is $movie")

        // Set the text inside the view
        tvTitle.text = movie.title
        tvOverview.text = movie.overview
        // Get the rating of the movie
        ratingBar.rating = movie.voteAverage.toFloat() // Downcast since rating takes a float, but we give it a double
    }
}