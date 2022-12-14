package com.alviandf.mycomposemovie.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.alviandf.mycomposemovie.R
import com.alviandf.mycomposemovie.domain.model.Movie
import com.alviandf.mycomposemovie.ui.theme.MyComposeMovieTheme

@Composable
fun MovieItem(
    movie: Movie,
    onClickListener: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
            .shadow(8.dp)
            .clip(RoundedCornerShape(4.dp))
            .clickable { onClickListener(movie.id) }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = movie.posterPath,
                error = painterResource(R.drawable.ic_error_image),
                contentDescription = movie.title,
                contentScale = ContentScale.Fit,
                modifier = Modifier
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieItemPreview() {
    MyComposeMovieTheme {
        MovieItem(
            movie = Movie(),
            onClickListener = { }
        )
    }
}