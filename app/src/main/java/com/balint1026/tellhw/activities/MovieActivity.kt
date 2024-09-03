package com.balint1026.tellhw.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.balint1026.tellhw.R
import com.balint1026.tellhw.adapters.ReviewAdapter
import com.balint1026.tellhw.compose.MovieItemScreen
import com.balint1026.tellhw.models.Review
import com.balint1026.tellhw.repositories.FirestoreRepository
import com.balint1026.tellhw.viewmodels.MovieViewModel
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth

class MovieActivity : AppCompatActivity() {

    private lateinit var firestoreRepository: FirestoreRepository

    private lateinit var moviePoster: ImageView
    private lateinit var movieTitle: TextView
    private lateinit var movieReleaseDate: TextView
    private lateinit var movieOverview: TextView
    private lateinit var movieRatingBar: RatingBar
    private lateinit var reviewInput: EditText
    private lateinit var submitReviewButton: Button

    private lateinit var reviewRecyclerView: RecyclerView

    private var movieId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MovieItemScreen(MovieViewModel())
        }
//        setContentView(R.layout.activity_movie)
//        firestoreRepository = FirestoreRepository()
//
//        moviePoster = findViewById(R.id.moviePoster)
//        movieTitle = findViewById(R.id.movieTitle)
//        movieReleaseDate = findViewById(R.id.movieReleaseDate)
//        movieOverview = findViewById(R.id.movieOverview)
//        movieRatingBar = findViewById(R.id.movieRatingBar)
//        reviewInput = findViewById(R.id.reviewInput)
//        submitReviewButton = findViewById(R.id.submitReviewButton)
//        reviewRecyclerView = findViewById(R.id.reviewRecyclerView)
//        reviewRecyclerView.layoutManager = LinearLayoutManager(this)
//        reviewRecyclerView.adapter = ReviewAdapter(emptyList())
//
//        movieId = intent.getIntExtra("MOVIE_ID", 0)
//
//        if (movieId == 0) {
//            finish()
//            return
//        }
    }


    private fun loadReviews() {
        firestoreRepository.getReviewsForMovie(movieId) { reviews ->
            (reviewRecyclerView.adapter as ReviewAdapter).updateReviews(reviews)
        }
    }
}
