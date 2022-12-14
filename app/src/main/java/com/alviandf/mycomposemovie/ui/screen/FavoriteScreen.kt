package com.alviandf.mycomposemovie.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells.Adaptive
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.alviandf.mycomposemovie.R
import com.alviandf.mycomposemovie.domain.model.Movie
import com.alviandf.mycomposemovie.ui.theme.GradientMainBackground
import com.alviandf.mycomposemovie.ui.viewmodel.MovieViewModel
import com.alviandf.mycomposemovie.ui.widgets.GenericState
import com.alviandf.mycomposemovie.ui.widgets.MovieItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel: MovieViewModel = koinViewModel(),
    onNavigateDetail: (Int) -> Unit,
) {
    viewModel.getFavorites()
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = GradientMainBackground
                )
            )
    ) {
        when {
            viewModel.favoriteMovies.isEmpty() -> {
                GenericState(
                    message = stringResource(id = R.string.message_empty_favorite),
                    drawable = R.drawable.ic_empty
                )
            }
            viewModel.favoriteMovies.isNotEmpty() -> {
                FavoriteContent(
                    favoriteMovies = viewModel.favoriteMovies,
                    onNavigateDetail = onNavigateDetail
                )
            }
        }
    }
}

@Composable
fun FavoriteContent(
    favoriteMovies: List<Movie>,
    onNavigateDetail: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = Adaptive(160.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(favoriteMovies, key = { it.id }) {
            MovieItem(
                movie = it,
                onClickListener = { id ->
                    onNavigateDetail(id)
                },
            )
        }
    }
}