package com.livingtechusa.gotjokes


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.livingtechusa.gotjokes.ui.build.BuildScreen


/**
 * Screen metadata for Rally.
 */
enum class JokesScreen(
    val icon: ImageVector,
    val body: @Composable ((String) -> Unit) -> Unit
) {
    Build(
        icon = Icons.Filled.Build,
        body = { BuildScreen() }
    ),
    Display(
        icon = Icons.Filled.AddAPhoto,
        body = { com.livingtechusa.gotjokes.ui.display.DisplayScreen() }
    ),
    Saved(
        icon = Icons.Filled.Save,
        body = { com.livingtechusa.gotjokes.ui.saved.SavedScreen() }
    );

    @Composable
    fun content(onScreenChange: (String) -> Unit) {
        body(onScreenChange)
    }

    companion object {
        fun fromRoute(route: String?): JokesScreen =
            when (route?.substringBefore("/")) {
                Build.name -> Build
                Display.name -> Display
                Saved.name -> Saved
                null -> Build
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}
