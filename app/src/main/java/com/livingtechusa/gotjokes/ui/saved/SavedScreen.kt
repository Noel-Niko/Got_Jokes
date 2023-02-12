package com.livingtechusa.gotjokes.ui.saved


import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.livingtechusa.gotjokes.ui.build.BuildEvent
import com.livingtechusa.gotjokes.ui.build.BuildViewModel
import com.livingtechusa.gotjokes.ui.components.ConfirmDeleteListDialog
import com.livingtechusa.gotjokes.ui.components.DisplayImgCard
import com.livingtechusa.gotjokes.ui.components.MemeImgCard

@Composable
fun SavedScreen() {
    val buildViewModel: BuildViewModel = viewModel(BuildViewModel::class.java)
    val jokeList by buildViewModel.jokes.observeAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
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
                    val delete = remember { mutableStateOf(false) }
                    if (delete.value) {
                        ConfirmDeleteListDialog(joke)
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                            .pointerInput(key1 = joke) {
                                detectTapGestures(
                                    onLongPress = {
                                        // Launch dialog
                                        delete.value = true
                                    },
                                    onTap = {
                                        buildViewModel.onTriggerEvent(BuildEvent.Share(joke))
                                    }
                                )
                            }
                    ) {
                        joke.imgURI?.let { MemeImgCard(uri = it) }
                            ?: DisplayImgCard(url = joke.imageUrl)
                        Spacer(modifier = Modifier.height(2.dp))
                    }
                }
            }
        }
    }
}
