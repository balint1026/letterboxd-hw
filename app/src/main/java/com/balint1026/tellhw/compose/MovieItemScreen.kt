package com.balint1026.tellhw.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.balint1026.tellhw.R
import com.balint1026.tellhw.models.Movie
import com.balint1026.tellhw.ui.theme.TellHWTheme
import com.balint1026.tellhw.viewmodels.MovieViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

const val baseURL = "https://image.tmdb.org/t/p/w500/"

@Composable
fun MovieItemScreen(movieViewModel: MovieViewModel) {
    val uiState by movieViewModel.uiState.collectAsStateWithLifecycle()
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieItemScreenContent(uiState: Movie?) {

    TellHWTheme {

        Surface(
            modifier = Modifier
                .background(color = colorResource(R.color.colorPrimary))
                .fillMaxSize()
        ) {

            Column {
                GlideImage(model = baseURL + uiState?.posterPath, contentDescription = "")
                Text(
                    text = uiState?.title ?: "",
                    fontSize = TextUnit(value = 28f, type = TextUnitType.Sp),
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    text = uiState?.overview ?: "",
                    fontSize = TextUnit(value = 24f, type = TextUnitType.Sp)
                )
            }
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
