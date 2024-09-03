package com.balint1026.tellhw.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.balint1026.tellhw.R
import com.balint1026.tellhw.adapters.MovieAdapter
import com.balint1026.tellhw.models.Movie
import com.balint1026.tellhw.repositories.MovieRepository
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var recyclerView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter
    private val movies: MutableList<Movie> = mutableListOf()
    private val movieRepository = MovieRepository()
    var currentPage = 1
    var isLoading = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        auth = FirebaseAuth.getInstance()

        movieAdapter = MovieAdapter(movies) { movie ->
            val intent = Intent(this, MovieActivity::class.java).apply {
                putExtra("MOVIE_ID", movie.id)
            }
            startActivity(intent)
        }
        recyclerView.adapter = movieAdapter

        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.adapter = movieAdapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!isLoading && (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0)) {
                    isLoading = true
                    currentPage++
                    fetchMovies(currentPage)
                }
            }
        })

        fetchMovies(currentPage)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val item = menu?.findItem(R.id.action_profile)
        val user = auth.currentUser
        item?.isVisible = user != null
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_login -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_profile -> {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun fetchMovies(page: Int) {
        movieRepository.getMovies(page) { movieList ->
            runOnUiThread {
                if (movieList != null) {
                    movies.addAll(movieList)
                    movieAdapter.notifyDataSetChanged()
                }
                isLoading = false
            }
        }
    }
}
