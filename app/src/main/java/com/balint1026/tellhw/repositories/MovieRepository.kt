package com.balint1026.tellhw.repositories

import com.balint1026.tellhw.models.Movie

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepository @Inject constructor (private val movieAPI: MovieAPI) {

  suspend fun getMovies(page: Int): MutableList<Movie> {

      val movies = withContext(Dispatchers.Default) {
          return@withContext movieAPI.getMovies(page).results.toMutableList()
      }
      return movies;

  }

    suspend fun getMovie(movieId: Int): Movie {
        val movie = withContext(Dispatchers.Default){
            return@withContext movieAPI.getMovie(movieId)
        }
        return movie
    }

}
