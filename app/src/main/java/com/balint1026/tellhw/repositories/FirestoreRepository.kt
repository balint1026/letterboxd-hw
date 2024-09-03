package com.balint1026.tellhw.repositories

import com.balint1026.tellhw.models.Movie
import com.balint1026.tellhw.models.MovieDetails
import com.google.firebase.firestore.FirebaseFirestore
import com.balint1026.tellhw.models.Review
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException
import io.github.cdimascio.dotenv.dotenv
import okhttp3.OkHttpClient


class FirestoreRepository {

    private val db = FirebaseFirestore.getInstance()
    private val client = OkHttpClient()
    private val dotenv = dotenv {
        directory = "/assets"
        filename = "env"
    }



    fun getMovies(page: Int, callback: (List<Movie>?) -> Unit) {
        val request = Request.Builder()
            .url("https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=en-US&page=$page&sort_by=popularity.desc")
            .get()
            .addHeader("accept", "application/json")
            .addHeader("Authorization", "Bearer ${dotenv["API_READ_ACCESS_TOKEN"]}")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                callback(null)
            }

            override fun onResponse(call: okhttp3.Call, response: Response) {
                if (response.isSuccessful) {
                    val jsonResponse = response.body?.string()
                    val movieList = parseMovies(jsonResponse)
                    callback(movieList)
                } else {
                    callback(null)
                }
            }
        })
    }

    private fun parseMovies(jsonResponse: String?): List<Movie> {
        val movies = mutableListOf<Movie>()
        if (jsonResponse.isNullOrEmpty()) return movies

        try {
            val jsonObject = JSONObject(jsonResponse)
            val resultsArray = jsonObject.getJSONArray("results")

            for (i in 0 until resultsArray.length()) {
                val movieObject = resultsArray.getJSONObject(i)
                val movie = Movie(
                    id = movieObject.getInt("id"),
                    title = movieObject.getString("title"),
                    overview = movieObject.getString("overview"),
                    posterPath = movieObject.getString("poster_path")
                )
                movies.add(movie)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return movies
    }

    fun getMovieDetails(movieId: Int, callback: (MovieDetails?) -> Unit) {
        val request = Request.Builder()
            .url("https://api.themoviedb.org/3/movie/$movieId?language=en-US")
            .get()
            .addHeader("accept", "application/json")
            .addHeader("Authorization", "Bearer ${dotenv["API_READ_ACCESS_TOKEN"]}")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                callback(null)
            }

            override fun onResponse(call: okhttp3.Call, response: Response) {
                if (response.isSuccessful) {
                    val jsonResponse = response.body?.string()
                    val movieDetails = parseMovieDetail(jsonResponse)
                    callback(movieDetails)
                } else {
                    callback(null)
                }
            }
        })
    }

    private fun parseMovieDetail(jsonResponse: String?): MovieDetails? {
        if (jsonResponse.isNullOrEmpty()) return null

        return try {
            val jsonObject = JSONObject(jsonResponse)
            MovieDetails(
                id = jsonObject.getInt("id"),
                title = jsonObject.getString("title"),
                overview = jsonObject.getString("overview"),
                posterPath = jsonObject.getString("poster_path"),
                releaseDate = jsonObject.getString("release_date"),
                voteAverage = jsonObject.getDouble("vote_average")
            )
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
    fun addReview(userId: String, movieId: Int, rating: Double, reviewText: String, onComplete: (Boolean) -> Unit) {
        val review = hashMapOf(
            "userId" to userId,
            "movieId" to movieId,
            "rating" to rating,
            "reviewText" to reviewText,
            "timestamp" to System.currentTimeMillis()
        )

        db.collection("reviews")
            .add(review)
            .addOnSuccessListener {
                onComplete(true)
            }
            .addOnFailureListener {
                onComplete(false)
            }
    }



    fun getReviewsForMovie(movieId: Int, onReviewsLoaded: (List<Review>) -> Unit) {
        db.collection("reviews")
            .whereEqualTo("movieId", movieId)
            .get()
            .addOnSuccessListener { result ->
                val reviews = result.map { document ->
                    Review(
                        document.getString("userId") ?: "",
                        document.getLong("movieId")?.toInt() ?: 0,
                        document.getDouble("rating") ?: 0.0,
                        document.getString("reviewText") ?: "",
                        document.getLong("timestamp") ?: 0
                    )
                }
                onReviewsLoaded(reviews)
            }
            .addOnFailureListener {
                onReviewsLoaded(emptyList())
            }
    }


    fun updateUserInfo(userId: String, email: String, displayName: String, onComplete: (Boolean) -> Unit) {
        val userMap = hashMapOf(
            "displayName" to displayName,
            "email" to email
        )

        db.collection("users").document(userId)
            .set(userMap)
            .addOnSuccessListener {
                onComplete(true)
            }
            .addOnFailureListener {
                onComplete(false)
            }
    }
}
