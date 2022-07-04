package com.livingtechusa.gotjokes.ui.build

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.livingtechusa.gotjokes.ui.components.BuildAJoke
import com.livingtechusa.gotjokes.ui.components.StatefulContentUpdater

@Composable
fun BuildScreen(
    modifier: Modifier = Modifier,
    buildViewModel: BuildViewModel = viewModel(),
    ){
    Column(modifier = modifier) {
        StatefulContentUpdater()

        BuildAJoke(
            loading = buildViewModel.loading,
            joke = buildViewModel.joke
        )
    }
}