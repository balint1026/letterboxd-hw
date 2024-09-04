package com.balint1026.tellhw.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.balint1026.tellhw.models.Movie
import com.balint1026.tellhw.models.Review
import com.balint1026.tellhw.repositories.FirestoreRepository
import com.balint1026.tellhw.repositories.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val firestoreRepository: FirestoreRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<Movie?>(
        Movie(
            id = 533535,
            title = "Deadpool & Wolverine",
            overview = "A listless Wade Wilson toils away in civilian life with his days as the morally flexible mercenary, Deadpool, behind him. But when his homeworld faces an existential threat, Wade must reluctantly suit-up again with an even more reluctant Wolverine.",
            posterPath = "8cdWjvZQUExUUTzyp4t6EDMubfO.jpg"
        )
    )
    val uiState = _uiState.asStateFlow()
    private val _reviewState = MutableStateFlow<List<Review>>(emptyList())
    val reviewState = _reviewState.asStateFlow()

    init {
        viewModelScope.launch { val y = movieRepository.getMovies(1) }
    }

    fun fetchMovieDetails(movieId: Int) {
        viewModelScope.launch {
            val movie = movieRepository.getMovie(movieId)
            _uiState.value = movie
        }
    }


}