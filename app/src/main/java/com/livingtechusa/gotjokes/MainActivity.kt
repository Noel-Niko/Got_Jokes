package com.livingtechusa.gotjokes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.livingtechusa.gotjokes.ui.build.BuildEvent
import com.livingtechusa.gotjokes.ui.build.BuildViewModel
import com.livingtechusa.gotjokes.ui.components.JokesTabRow
import com.livingtechusa.gotjokes.ui.theme.JokesTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var app: BaseApplication
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JokeApp()
        }
    }
}

@Composable
fun JokeApp() {
    val buildViewModel: BuildViewModel = viewModel(BuildViewModel::class.java)
    JokesTheme() {
        val allScreens = JokesScreen.values().toList()
        var currentScreen by rememberSaveable { mutableStateOf(JokesScreen.Build) }
        Scaffold(
            topBar = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.2f)
                            .align(Alignment.CenterVertically)
                    ) {
                        IconButton(
                            modifier = Modifier
                                .wrapContentWidth(align = Alignment.Start),
                            onClick = {
                                buildViewModel.onTriggerEvent(BuildEvent.GetNewImage)
                            }) {
                            Icon(Icons.Filled.Refresh, "Refresh")
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterVertically)
                    ) {
                        JokesTabRow(
                            allScreens = allScreens,
                            onTabSelected = { screen -> currentScreen = screen },
                            currentScreen = currentScreen
                        )
                    }
                }
            }
        ) { innerPadding ->
            Box(Modifier.padding(innerPadding)) {
                currentScreen.content(
                    onScreenChange = { screen ->
                        currentScreen = JokesScreen.valueOf(screen)
                    }
                )
            }
        }
    }
}