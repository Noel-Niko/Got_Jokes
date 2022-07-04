package com.livingtechusa.gotjokes.ui.build

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.livingtechusa.gotjokes.data.api.model.imgFlip

@Composable
fun BuildScreen(){
    val modifier: Modifier = Modifier
    val buildViewModel: BuildViewModel = viewModel(BuildViewModel::class.java)
    val state by buildViewModel.state.collectAsState()

    LazyColumn {
        if(state.isEmpty()){
            item {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center)
                )
            }
        }
        items(state){ meme: imgFlip.Data.Meme ->
            MemeImgCard(meme = meme)
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
}

@Composable
fun MemeImgCard(meme: imgFlip.Data.Meme) {
    val imagePainter = rememberAsyncImagePainter(model = meme.url)
    Card(shape = MaterialTheme.shapes.medium, modifier = Modifier.padding(16.dp)){
        Box{
            Image(
                painter = imagePainter,
                contentDescription = "Random Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.FillBounds
            )

            Surface(
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.3f),
                modifier = Modifier.align((Alignment.BottomCenter)),
                contentColor = MaterialTheme.colors.surface
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ){
                    Text(text = "Meme Title: ${meme.name}")
                }
            }
        }
    }

}