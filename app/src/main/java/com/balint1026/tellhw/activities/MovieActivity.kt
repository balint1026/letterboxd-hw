package com.balint1026.tellhw.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {

    private val viewModel: MovieViewModel by viewModels()


    private lateinit var firestoreRepository: FirestoreRepository

    private lateinit var reviewRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val movieId = intent.getIntExtra("MOVIE_ID", -1)
        setContent {
            MovieItemScreen(viewModel, movieId)
        }
    }
}
