package com.livingtechusa.gotjokes.ui.display

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.livingtechusa.gotjokes.ui.build.BuildEvent
import com.livingtechusa.gotjokes.ui.build.BuildViewModel
import com.livingtechusa.gotjokes.ui.components.MemeImgCard
import com.livingtechusa.gotjokes.ui.theme.JokesTheme

@Composable
fun DisplayScreen() {
    val buildViewModel: BuildViewModel = viewModel(BuildViewModel::class.java)
    val caption by buildViewModel.caption.collectAsState()
    val image by buildViewModel.imageUrl.collectAsState()
    val scaffoldState = rememberScaffoldState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()

    ) {
        // TODO: animate the progress icon to be 3 dots moving
        if (image == null) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(25.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            }
        } else {
            item {
                //TODO: USE LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    Spacer(Modifier.height(16.dp))
                    if (image != null) {
                        MemeImgCard(url = image!!)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = caption,
                        onValueChange = {
                            buildViewModel.onTriggerEvent(BuildEvent.UpdateCaption(it))
                        },
                        label = { Text("Caption: What's your best idea?") }
                    )
                }
            }
        }
    }
}
