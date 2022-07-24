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

//    val scaffoldState = rememberScaffoldState()
    JokesTheme() {
        val allScreens = JokesScreen.values().toList()
//        val navController = rememberNavController()
//        val currentBackStack by navController.currentBackStackEntryAsState()
//        val currentDestination = currentBackStack?.destination
        var currentScreen by rememberSaveable { mutableStateOf(JokesScreen.Build) }
        Scaffold(
            topBar = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                ) {
                    Column(modifier = Modifier.fillMaxWidth(0.2f)
                        .align(Alignment.CenterVertically)
                        //.background(color = MaterialTheme.colors.primaryVariant)
                    ) {
                        IconButton(
                            modifier = Modifier
                                .wrapContentWidth(align = Alignment.Start),
                                //.background(color = MaterialTheme.colors.primaryVariant),
                            onClick = {
                                buildViewModel.onTriggerEvent(BuildEvent.GetNewImage)
                            }) {
                            Icon(Icons.Filled.Refresh, "Refresh")
                        }
                    }
                    Column(modifier = Modifier.fillMaxWidth()
                        .align(Alignment.CenterVertically)
                        //.background(color = MaterialTheme.colors.primaryVariant)
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

//        JokesTheme() {
//        val navController = rememberNavController()
//        val currentBackStack by navController.currentBackStackEntryAsState()
//        val currentDestination = currentBackStack?.destination
//        val currentScreen =
//            jokeTabRowScreens.find { it.route == currentDestination?.route } ?: Build
//
//        Surface(
//            modifier = Modifier.fillMaxSize(),
//            color = MaterialTheme.colors.background
//        ) {
//    modifier = Modifier
//        .fillMaxWidth()
//        .wrapContentWidth(Alignment.End),
//    navigationIcon = {
//        IconButton(
//            modifier = Modifier.wrapContentWidth(align = Alignment.Start),
//            onClick = {
//                buildViewModel.onTriggerEvent(BuildEvent.GetNewImage)
//            }) {
//            Icon(Icons.Filled.Refresh, "Refresh")
//        }
//        IconButton(
//            modifier = Modifier.wrapContentWidth(align = Alignment.End),
//            onClick = {
//                buildViewModel.onTriggerEvent(BuildEvent.GoToDisplayScreen(navController))
//            }) {
//            Icon(Icons.Default.Build, "Build") //painter = painterResource(id = R.drawable.ic_save_24px),
//        }
//        IconButton(
//            modifier = Modifier.wrapContentWidth(align = Alignment.End),
//            onClick = {
//                buildViewModel.onTriggerEvent(BuildEvent.Save(navController, buildViewModel.imageUrl.value.toString(), buildViewModel.caption.value))
//            }) {
//            Icon(Icons.Default.Save, "Save") //painter = painterResource(id = R.drawable.ic_save_24px),
//        }
//        IconButton(
//            modifier = Modifier.wrapContentWidth(align = Alignment.End),
//            onClick = {
//                buildViewModel.onTriggerEvent(BuildEvent.GoToDisplayScreen(navController))
//            }) {
//            Icon(Icons.Default.Image, "Memes") //painter = painterResource(id = R.drawable.ic_save_24px),
//        }
//    },
//    title = {
//        Text(
//            modifier = Modifier
//                .fillMaxWidth()
//                .wrapContentWidth(align = Alignment.CenterHorizontally),
//            text = "Got Jokes?"
//        )
//    },
//    backgroundColor = MaterialTheme.colors.primary,
//    contentColor = Color.White,
//    elevation = 10.dp
//
//    )
//}




