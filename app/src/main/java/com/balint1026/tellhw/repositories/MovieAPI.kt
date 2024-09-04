package com.balint1026.tellhw.repositories

import com.balint1026.tellhw.models.Movie
import com.balint1026.tellhw.models.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {
    @GET("discover/movie")
    suspend fun getMovies(
        @Query("page") page: Int
    ): MoviesResponse

    @GET("movie/{movieId}?language=en-US")
    suspend fun getMovie(
        @Path("movieId") movieId: Int
    ): Movie
}