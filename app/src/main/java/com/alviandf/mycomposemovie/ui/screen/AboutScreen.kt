package com.alviandf.mycomposemovie.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alviandf.mycomposemovie.R
import com.alviandf.mycomposemovie.ui.theme.GradientMainBackground
import com.alviandf.mycomposemovie.ui.theme.MyComposeMovieTheme

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = GradientMainBackground
                )
            )
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_developer),
            contentDescription = stringResource(id = R.string.image_about),
            modifier = Modifier
                .clip(CircleShape)
                .size(300.dp)
                .align(CenterHorizontally)
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = stringResource(id = R.string.developer_name),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .fillMaxWidth(),
            color = Color.White
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = stringResource(id = R.string.developer_email),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            modifier = Modifier.fillMaxWidth(),
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    MyComposeMovieTheme {
        AboutScreen()
    }
}