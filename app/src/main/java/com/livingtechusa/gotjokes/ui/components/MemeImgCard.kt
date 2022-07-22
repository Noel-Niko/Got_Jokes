package com.livingtechusa.gotjokes.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.livingtechusa.gotjokes.ui.build.BuildEvent
import com.livingtechusa.gotjokes.ui.build.BuildScreenLandscape
import com.livingtechusa.gotjokes.ui.build.BuildViewModel

@Composable
fun MemeImgCard(url: String) {
    val buildViewModel: BuildViewModel = viewModel(BuildViewModel::class.java)
    val caption by buildViewModel.caption.collectAsState()
    val configuration = LocalConfiguration.current
    val scrollState = rememberScrollState()
    val maxDimention: Dp =
        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            configuration.screenWidthDp.dp
        } else {
            configuration.screenHeightDp.dp
        }

    val imagePainter = rememberImagePainter(
        data = url,
        builder = {
            allowHardware(false)
        }
    )
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.width(maxDimention / 2.5f),
        elevation = 3.dp
    ) {
        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Column(
              modifier = Modifier
                  .fillMaxSize()
                  .verticalScroll(scrollState,true)
            ) {
                Image(
                    painter = imagePainter,
                    contentDescription = "Random Image",
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .height(200.dp)
                        .align(Alignment.CenterHorizontally),
                   contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.width(8.dp))
                TextField(
                    modifier = Modifier
                        .padding(8.dp)
                        .wrapContentWidth(),
                    value = caption,
                    onValueChange = {
                        buildViewModel.onTriggerEvent(BuildEvent.UpdateCaption(it))
                    },
                    label = { Text("Caption: What's your best idea?") }
                )
            }
        } else {
            Column(
                modifier = Modifier
                    .height(maxDimention / 3)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = imagePainter,
                    contentDescription = "Random Image",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}