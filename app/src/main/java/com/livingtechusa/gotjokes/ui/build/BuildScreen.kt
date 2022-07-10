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
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.livingtechusa.gotjokes.data.api.model.ImgFlip
import com.livingtechusa.gotjokes.data.api.model.YoMamma
import com.livingtechusa.gotjokes.ui.theme.JokesTheme


@Composable
fun BuildScreen() {
    val buildViewModel: BuildViewModel = viewModel(BuildViewModel::class.java)
    val image by buildViewModel.imgFlipMeme.collectAsState()
    val yoMamma by buildViewModel.yoMamma.collectAsState()
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
            BuildLayout(Modifier.padding(padding), image, yoMamma)
        }

    }
}

@Composable
fun BuildLayout(modifier: Modifier, image: ImgFlip.Data.Meme?, yoMamma: YoMamma?) {
    var caption by rememberSaveable() { mutableStateOf("My Caption") }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()

    ) {
        //TODO: reduce to one randomly selected picture
        // add placeholders for the text
        //animate the progress icon to be 3 dots moving
        if (image?.url == null && yoMamma?.joke == null) {
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
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    Spacer(Modifier.height(16.dp))
                    if (image != null) {
                        MemeImgCard(meme = image)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = caption,
                        onValueChange = {
                            caption = it
                        },
                        label = { Text("Caption") }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = yoMamma?.joke ?: "Nuttin to see here."
                    )
                    //                        Text(
                    //                            modifier = Modifier.fillMaxWidth(),
                    //                            text = "Meme Title: ${image.name}"
                    //                        )

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
fun MemeImgCard(meme: ImgFlip.Data.Meme) {
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