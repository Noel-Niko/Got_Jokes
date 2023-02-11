package com.livingtechusa.gotjokes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.livingtechusa.gotjokes.ui.build.BuildScreen
import com.livingtechusa.gotjokes.ui.display.DisplayScreen
import com.livingtechusa.gotjokes.ui.saved.SavedScreen

@Composable
fun NavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Build.route,
        modifier = modifier
    ) {
        composable(route = Build.route) {
            BuildScreen()
        }
        composable(
            route = Display.route,
        ) {
            DisplayScreen()
        }
        composable(route = Saved.route) {
            SavedScreen()
        }
    }
}


fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) { launchSingleTop = true }