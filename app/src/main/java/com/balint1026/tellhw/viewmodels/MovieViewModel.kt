package com.balint1026.tellhw.viewmodels

import androidx.lifecycle.ViewModel
import com.balint1026.tellhw.models.Movie
import com.balint1026.tellhw.models.Review
import com.balint1026.tellhw.repositories.FirestoreRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MovieViewModel : ViewModel() {


    private var firestoreRepository: FirestoreRepository = FirestoreRepository()

    private val _uiState = MutableStateFlow<Movie?>( Movie(
        id = 533535,
        title = "Deadpool & Wolverine",
        overview = "A listless Wade Wilson toils away in civilian life with his days as the morally flexible mercenary, Deadpool, behind him. But when his homeworld faces an existential threat, Wade must reluctantly suit-up again with an even more reluctant Wolverine.",
        posterPath = "8cdWjvZQUExUUTzyp4t6EDMubfO.jpg"
    ))
    val uiState = _uiState.asStateFlow()
    private val _reviewState = MutableStateFlow<List<Review>>(emptyList())
    val reviewState = _reviewState.asStateFlow()


/*
    private fun loadMovieDetails() {
        firestoreRepository.getMovieDetails(movieId) { movie ->
            if (movie != null) {
                movieTitle.text = movie.title
                movieReleaseDate.text = movie.releaseDate
                movieOverview.text = movie.overview
                Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                    .into(moviePoster)
                movieRatingBar.rating = movie.voteAverage.toFloat() / 2
                loadReviews()
            }
        }
    }
    private fun setupReviewSubmission() {
        submitReviewButton.setOnClickListener {
            val reviewText = reviewInput.text.toString()
            val rating = movieRatingBar.rating.toDouble()
            val userId = FirebaseAuth.getInstance().currentUser?.uid
            val timestamp = System.currentTimeMillis()

            if (userId != null && reviewText.isNotEmpty()) {
                val review = Review(userId, movieId, rating, reviewText, timestamp)
                firestoreRepository.addReview(
                    userId = review.userId,
                    movieId = review.movieId,
                    rating = review.rating,
                    reviewText = review.reviewText
                ) { success ->
                    if (success) {
                        reviewInput.text.clear()
                        loadReviews()
                    }
                }
            }
        }
    }
 */

}