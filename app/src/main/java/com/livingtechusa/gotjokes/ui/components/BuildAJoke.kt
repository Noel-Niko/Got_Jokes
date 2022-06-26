package com.livingtechusa.gotjokes.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.livingtechusa.gotjokes.domain.model.Joke
import com.livingtechusa.gotjokes.domain.util.DEFAULT_IMAGE
import com.livingtechusa.gotjokes.domain.util.LoadPicture

@Composable
fun BuildAJoke(
    loading: Boolean,
    joke: Joke,
    scaffoldState: ScaffoldState,
    ) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        val img = LoadPicture(defaultImage = DEFAULT_IMAGE).value
        if (loading && joke.image.isNullOrEmpty()) {
            if (img?.asImageBitmap() != null) {
                //ShimmerAnimation()
            while(loading) {
                img?.asImageBitmap()?.let {
                    Image(
                        modifier = Modifier.wrapContentWidth(align = Alignment.End),
                        bitmap = it,
                        contentDescription = "Loading True"
                    )
                }
                LinearProgressIndicator(
                    modifier = Modifier
                        .wrapContentWidth(align = Alignment.CenterHorizontally),
                    color = MaterialTheme.colors.primary
                )
            }
            }
        } else {
            joke?.let{
                BuildView(joke = it)
            }
        }
    }
}