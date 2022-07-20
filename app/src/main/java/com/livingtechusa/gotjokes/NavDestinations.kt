package com.livingtechusa.gotjokes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.livingtechusa.gotjokes.ui.build.BuildScreen
import com.livingtechusa.gotjokes.ui.display.DisplayScreen
import com.livingtechusa.gotjokes.ui.saved.SavedScreen


/**
 * Contract for information needed on every Joke navigation destination
 */
interface JokeDestination {
    val icon: ImageVector
    val route: String
}

/**
 * Got Jokes app navigation destinations
 */
object Build : JokeDestination {
    override val icon = Icons.Filled.Build
    override val route = "build_screen"
}

object Display : JokeDestination {
    override val icon = Icons.Filled.Send
    override val route = "display_screen"
//    const val accountTypeArg = "meme"
//    val routeWithArgs = "$route/{$accountTypeArg}"
//    val arguments = listOf(
//        navArgument(accountTypeArg) { type = NavType.StringType }
//    )
}

object Saved : JokeDestination {
    override val icon = Icons.Filled.Favorite
    override val route = "display_screen"
}

// Screens to be displayed in the top TabRow
val jokeTabRowScreens = listOf(Build, Display, Saved)
