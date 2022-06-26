package com.livingtechusa.gotjokes.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import com.livingtechusa.gotjokes.domain.model.Joke
import com.livingtechusa.gotjokes.domain.util.DEFAULT_IMAGE
import com.livingtechusa.gotjokes.domain.util.LoadPicture


@Composable
fun BuildView(
    joke: Joke
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
    ) {
        //Check each field and display if not null
        joke.image?.let { url ->
            val image = LoadPicture(url = url, defaultImage = DEFAULT_IMAGE).value  //a bitMap
            image?.let { img ->
                Image(
                    bitmap = img.asImageBitmap(),
                    contentDescription = "Waiting Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.3f),
                    contentScale = ContentScale.FillHeight
                )
            }
        }
        Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Text(
                    text = "Charlie Chaplin",
                    modifier = Modifier.fillMaxWidth()
                        .wrapContentWidth(align = Alignment.Start),
                    style = MaterialTheme.typography.h3
                )
            }
        }

    }

}