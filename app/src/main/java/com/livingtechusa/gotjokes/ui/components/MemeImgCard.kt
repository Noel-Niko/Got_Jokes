package com.livingtechusa.gotjokes.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@Composable
fun MemeImgCard(url: String) {
    val configuration = LocalConfiguration.current

    val screenHeight = configuration.screenHeightDp.dp

    val imagePainter = rememberImagePainter(
        data = url,
        builder = {
            allowHardware(false)
        }
    )
    Card(shape = MaterialTheme.shapes.medium, modifier = Modifier.padding(16.dp)) {
        Box {
            Image(
                painter = imagePainter,
                contentDescription = "Random Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(screenHeight / 3),
                contentScale = ContentScale.Fit
            )
        }
    }
}
