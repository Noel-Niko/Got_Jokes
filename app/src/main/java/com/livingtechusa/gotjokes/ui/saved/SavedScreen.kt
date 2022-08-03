package com.livingtechusa.gotjokes.ui.saved


import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.livingtechusa.gotjokes.ui.build.BuildEvent
import com.livingtechusa.gotjokes.ui.build.BuildViewModel
import com.livingtechusa.gotjokes.ui.components.DisplayImgCard
import androidx.compose.runtime.livedata.observeAsState
import com.livingtechusa.gotjokes.ui.components.MemeImgCard

@Composable
fun SavedScreen() {
    val configuration = LocalConfiguration.current
    if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        SavedScreenLandscape()
    } else {
        val buildViewModel: BuildViewModel = viewModel(BuildViewModel::class.java)

        val jokeList by buildViewModel.jokes.observeAsState()

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // TODO: animate the progress icon to be 3 dots moving
            if (jokeList.isNullOrEmpty()) {
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
                    jokeList?.forEach { joke ->
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp)
                                .clickable {
                                           //TODO: connect to send or delete via toast
                                    //buildViewModel.onTriggerEvent(BuildEvent.Delete(joke))
                                },

                        ) {
                            joke.imgURI?.let { MemeImgCard(uri = it) } ?: DisplayImgCard(url = joke.imageUrl)
                            //DisplayImgCard(url = joke.imageUrl)
                            Spacer(modifier = Modifier.height(2.dp))
                        }
                    }
                }
            }
        }
    }
}