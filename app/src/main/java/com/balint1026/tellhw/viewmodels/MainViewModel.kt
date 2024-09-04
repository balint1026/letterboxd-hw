package com.balint1026.tellhw.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.balint1026.tellhw.models.Movie
import com.balint1026.tellhw.repositories.FirestoreRepository
import com.balint1026.tellhw.repositories.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val firestoreRepository: FirestoreRepository
) : ViewModel() {
    fun fetchMovies(currentPage: Int) {
        viewModelScope.launch {
            _uiState.value = movieRepository.getMovies(currentPage)
        }
    }

    private val _uiState = MutableStateFlow<MutableList<Movie>>(mutableListOf())
    val uiState = _uiState.asStateFlow()

    init {
        fetchMovies(1)
    }
}


