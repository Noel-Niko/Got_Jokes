package com.livingtechusa.gotjokes.ui.saved


import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.livingtechusa.gotjokes.R
import com.livingtechusa.gotjokes.ui.build.BuildEvent
import com.livingtechusa.gotjokes.ui.build.BuildViewModel
import com.livingtechusa.gotjokes.ui.components.MemeImgCard
import androidx.compose.runtime.livedata.observeAsState

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
                                .fillMaxWidth()
                                .padding(8.dp)
                                .clickable {
                                    buildViewModel.onTriggerEvent(BuildEvent.Delete(joke))
                                },

                        ) {
                            MemeImgCard(url = joke.imageUrl)
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                modifier = Modifier.fillMaxSize(),
                                text = joke.caption
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }
        }
    }
}