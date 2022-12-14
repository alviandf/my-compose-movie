package com.alviandf.mycomposemovie.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alviandf.mycomposemovie.R
import com.alviandf.mycomposemovie.ui.theme.MyComposeMovieTheme

@Composable
fun GenericState(
    message: String,
    drawable: Int,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier.padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = drawable),
            contentDescription = stringResource(id = R.string.image_empty),
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = message,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GenericStatePreview() {
    MyComposeMovieTheme {
        GenericState(
            message = stringResource(id = R.string.message_empty_search),
            drawable = R.drawable.ic_empty
        )
    }
}