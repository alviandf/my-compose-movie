package com.alviandf.mycomposemovie.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.alviandf.mycomposemovie.R
import com.alviandf.mycomposemovie.data.Resource.Empty
import com.alviandf.mycomposemovie.data.Resource.Error
import com.alviandf.mycomposemovie.data.Resource.Loading
import com.alviandf.mycomposemovie.data.Resource.Success
import com.alviandf.mycomposemovie.domain.model.Movie
import com.alviandf.mycomposemovie.ui.theme.Black100
import com.alviandf.mycomposemovie.ui.theme.GradientMainBackground
import com.alviandf.mycomposemovie.ui.theme.MyComposeMovieTheme
import com.alviandf.mycomposemovie.ui.viewmodel.MovieViewModel
import com.alviandf.mycomposemovie.ui.widgets.GenericState
import com.alviandf.mycomposemovie.utils.changeDateFormat
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreen(
    movieId: Int,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MovieViewModel = koinViewModel(),
) {
    val state by viewModel.detailMovie.observeAsState()

    Box(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        when (state) {
            is Empty -> {
                GenericState(message = stringResource(id = R.string.message_error), drawable = R.drawable.ic_error)
            }
            is Error -> {
                GenericState(message = stringResource(id = R.string.message_error), drawable = R.drawable.ic_error)
            }
            is Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            is Success -> {
                DetailContent(detailMovie = state?.data!!, navigateBack = navigateBack)
            }
            else -> {
                viewModel.getDetail(movieId)
            }
        }
    }
}

@Composable
fun DetailContent(
    detailMovie: Movie,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MovieViewModel = koinViewModel(),
) {
    val isFavorite = viewModel.isFavorite

    viewModel.checkFavorite(detailMovie.id)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = GradientMainBackground
                )
            )
            .verticalScroll(rememberScrollState())
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = detailMovie.backdropPath,
                contentDescription = detailMovie.title,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
            )
            FloatingActionButton(
                onClick = { navigateBack() },
                backgroundColor = Black100,
                contentColor = Color.White,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 16.dp, top = 16.dp)
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = stringResource(id = R.string.action_back))
            }

            FloatingActionButton(
                onClick = {
                    if (isFavorite) {
                        viewModel.deleteFavorite(detailMovie)
                    } else {
                        viewModel.insertFavorite(detailMovie)
                    }
                    viewModel.checkFavorite(detailMovie.id)
                },
                backgroundColor = Black100,
                contentColor = Color.White,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 16.dp, end = 16.dp)
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = stringResource(id = R.string.action_back)
                )
            }
        }
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = detailMovie.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Black,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = Color.White
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = detailMovie.overview,
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                color = Color.White
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = stringResource(id = R.string.label_release_date),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = changeDateFormat(detailMovie.releaseDate, "yyyy-MM-dd", "dd-MM-yyyy"),
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailContentPreview() {
    MyComposeMovieTheme() {
        DetailContent(
            Movie(
            ), {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    MyComposeMovieTheme {
        DetailScreen(movieId = 114410, navigateBack = { })
    }
}