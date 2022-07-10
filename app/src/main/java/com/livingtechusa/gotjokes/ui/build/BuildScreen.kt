package com.livingtechusa.gotjokes.ui.build

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.livingtechusa.gotjokes.data.api.model.imgFlip
import com.livingtechusa.gotjokes.ui.theme.JokesTheme


@Composable
fun BuildScreen() {
    val buildViewModel: BuildViewModel = viewModel(BuildViewModel::class.java)
    val image by buildViewModel.imgFlipMeme.collectAsState()
    val scaffoldState = rememberScaffoldState()

    JokesTheme() {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.End),
                    title = {
                        Text(
                            modifier = Modifier.wrapContentWidth(align = Alignment.Start),
                            text = "Got Jokes?"
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            modifier = Modifier.wrapContentWidth(align = Alignment.End),
                            onClick = {
                                buildViewModel.onTriggerEvent(BuildEvent.GetNewImgFlipImage)
                            }) {
                            Icon(Icons.Filled.Refresh, "refresh")
                        }
                    },
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = Color.White,
                    elevation = 10.dp

                )
            }
        ) { padding ->
            BuildLayout(Modifier.padding(padding), image)
        }

    }
}

@Composable
fun BuildLayout(modifier: Modifier, image: imgFlip.Data.Meme?) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()

    ) {
        //TODO: reduce to one randomly selected picture
        // add placeholders for the text
        //animate the progress icon to be 3 dots moving
        if (image != null) {
            if (image.url.isEmpty()) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = ((LocalConfiguration.current.screenHeightDp) / 3).dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }
                }
            } else {
                item {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Spacer(Modifier.height(16.dp))
                        MemeImgCard(meme = image)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Meme Title: ${image.name}"
                        )

                    }
                }
            }
        }
    }
}


//    Column(modifier = modifier) {
//        StatefulContentUpdater()
//
//        BuildAJoke(
//            loading = buildViewModel.loading,
//            joke = buildViewModel.joke
//        )
//    }

@Composable
fun MemeImgCard(meme: imgFlip.Data.Meme) {
    val imagePainter = rememberAsyncImagePainter(model = meme.url)
    Card(shape = MaterialTheme.shapes.medium, modifier = Modifier.padding(16.dp)) {
        Box {
            Image(
                painter = imagePainter,
                contentDescription = "Random Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Fit
            )

            //            Surface(
            //                color = MaterialTheme.colors.onSurface.copy(alpha = 0.3f),
            //                modifier = Modifier.align((Alignment.BottomCenter)),
            //                contentColor = MaterialTheme.colors.surface
            //            ) {
            //                Column(
            //                    modifier = Modifier
            //                        .fillMaxWidth()
            //                        .padding(4.dp)
            //                ) {
            //                    Text(text = "Meme Title: ${meme.name}")
            //                }
            //            }
        }
    }

}