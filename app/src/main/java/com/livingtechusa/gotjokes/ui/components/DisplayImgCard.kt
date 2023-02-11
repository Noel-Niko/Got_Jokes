package com.livingtechusa.gotjokes.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.livingtechusa.gotjokes.ui.build.BuildViewModel

@Composable
fun DisplayImgCard(url: String) {
    val buildViewModel: BuildViewModel = viewModel(BuildViewModel::class.java)
    val caption by buildViewModel.caption.collectAsState()
    val configuration = LocalConfiguration.current
    val scrollState = rememberScrollState()
    val maxDimention: Dp =
        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            configuration.screenHeightDp.dp
        } else {
            configuration.screenHeightDp.dp / 2.5f
        }

    val imagePainter = rememberImagePainter(
        data = url,
        builder = {
            allowHardware(false)
        }
    )
    if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        Card(
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .width(maxDimention)
                .fillMaxHeight()
                .padding(4.dp),
            elevation = 6.dp
        ) {
            Image(
                painter = imagePainter,
                contentDescription = "Random Image",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Fit
            )
        }
    } else {
        Card(
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .height(maxDimention)
                .fillMaxWidth(),
            elevation = 6.dp
        ) {
            Image(
                painter = imagePainter,
                contentDescription = "Random Image",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Fit
            )
        }
    }
}