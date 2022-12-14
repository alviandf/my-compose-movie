package com.alviandf.mycomposemovie.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alviandf.mycomposemovie.R
import com.alviandf.mycomposemovie.data.Resource
import com.alviandf.mycomposemovie.domain.model.Movie
import com.alviandf.mycomposemovie.ui.theme.GradientMainBackground
import com.alviandf.mycomposemovie.ui.theme.MyComposeMovieTheme
import com.alviandf.mycomposemovie.ui.viewmodel.MovieViewModel
import com.alviandf.mycomposemovie.ui.widgets.GenericState
import com.alviandf.mycomposemovie.ui.widgets.MovieItem
import com.alviandf.mycomposemovie.ui.widgets.SearchView
import org.koin.androidx.compose.koinViewModel

@Composable
fun PopularMovieScreen(
    modifier: Modifier = Modifier,
    viewModel: MovieViewModel = koinViewModel(),
    onNavigateDetail: (Int) -> Unit,
) {
    val state by viewModel.movies.observeAsState()
    val query = rememberSaveable(stateSaver = TextFieldValue.Saver){ mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = modifier.background(
            brush = Brush.verticalGradient(
                colors = GradientMainBackground
            )
        )
    ) {
        SearchView(
            query = query,
            onSearch = {
                if (it.isNotEmpty()) viewModel.getSearch(it) else viewModel.getPopularMovies()
            }
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            when (state) {
                is Resource.Empty -> {
                    GenericState(
                        message = stringResource(id = R.string.message_empty_search),
                        drawable = R.drawable.ic_empty
                    )
                }
                is Resource.Error -> {
                    GenericState(
                        message = state?.message.toString(),
                        drawable = R.drawable.ic_error
                    )
                }
                is Resource.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                is Resource.Success -> {
                    PopularContent(
                        popularMovies = state?.data!!,
                        onNavigateDetail = onNavigateDetail
                    )
                }
                else -> {
                    viewModel.getPopularMovies()
                }
            }
        }
    }
}

@Composable
fun PopularContent(
    popularMovies: List<Movie>,
    onNavigateDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(popularMovies, key = { it.id }) {
            MovieItem(
                movie = it,
                onClickListener = { id ->
                    onNavigateDetail(id)
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PopularScreenPreview() {
    MyComposeMovieTheme {
        PopularMovieScreen(onNavigateDetail = {})
    }
}