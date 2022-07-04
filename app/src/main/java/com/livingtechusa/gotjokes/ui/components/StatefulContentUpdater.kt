package com.livingtechusa.gotjokes.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.livingtechusa.gotjokes.domain.model.Joke
import com.livingtechusa.gotjokes.ui.build.BuildScreen

@Composable
fun StatefulContentUpdater(modifier: Modifier = Modifier) {
        var joke by rememberSaveable  { mutableStateOf(Joke()) }
    StatelessContentUpdater(
        joke = joke,
        onChange = {},
        modifier = Modifier
    )

}

@Composable
fun StatelessContentUpdater(
    joke: Joke, onChange: () -> Unit, modifier: Modifier = Modifier){
    BuildAJoke(loading = false, joke = joke)
}

