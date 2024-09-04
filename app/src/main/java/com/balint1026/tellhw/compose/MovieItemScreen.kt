package com.balint1026.tellhw.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.balint1026.tellhw.R
import com.balint1026.tellhw.models.Movie
import com.balint1026.tellhw.ui.theme.TellHWTheme
import com.balint1026.tellhw.viewmodels.MovieViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle

const val baseURL = "https://image.tmdb.org/t/p/w500/"

@Composable
fun MovieItemScreen(movieViewModel: MovieViewModel, movieId: Int) {
    LaunchedEffect(movieId) {
        movieViewModel.fetchMovieDetails(movieId)
    }

    val uiState by movieViewModel.uiState.collectAsStateWithLifecycle()

    MovieItemScreenContent(uiState)
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieItemScreenContent(uiState: Movie?) {
    var reviewText by remember { mutableStateOf(TextFieldValue("")) }

    TellHWTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black)
        ) {
            Column {
                GlideImage(
                    model = baseURL + uiState?.posterPath,
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = uiState?.title ?: "",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))



                Text(
                    text = uiState?.overview ?: "",
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                RatingBar(
                    rating = 3.5f,
                    modifier = Modifier.padding(0.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                BasicTextField(
                    value = reviewText,
                    onValueChange = { reviewText = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray)
                        .height(50.dp),
                    decorationBox = { innerTextField ->
                        if (reviewText.text.isEmpty()) {
                            Text(
                                text = "Write your review here",
                                color = Color.Gray
                            )
                        }
                        innerTextField()
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(onClick = {  }) {
                    Text(text = "Submit Review")
                }

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(9) { reviewIndex ->
                        Text(text = "Review ${reviewIndex + 1}", modifier = Modifier.padding(8.dp))
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}

@Composable
fun RatingBar(rating: Float, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        repeat(5) { index ->
            val starIcon = if (index < rating) "★" else "☆"
            Text(
                text = starIcon,
                fontSize = 24.sp,
                color = Color.Yellow
            )
        }
    }
}

@Preview
@Composable
private fun MovieItemScreenPreview() {
    MovieItemScreenContent(
        Movie(
            id = 533535,
            title = "Deadpool & Wolverine",
            overview = "A listless Wade Wilson toils away in civilian life with his days as the morally flexible mercenary, Deadpool, behind him. But when his homeworld faces an existential threat, Wade must reluctantly suit-up again with an even more reluctant Wolverine.",
            posterPath = "8cdWjvZQUExUUTzyp4t6EDMubfO.jpg"
        )
    )
}
