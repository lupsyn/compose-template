package com.ebdz.compose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ebdz.compose.presentation.home.HomeScreen
import com.ebdz.navigation.Destinations
import com.ebdz.preference.presentation.About

/**
 * Navigation Graph to control the app navigation.
 *
 * @param startDestination the start destination of the graph
 */
@Composable
fun NavGraph(startDestination: String = Destinations.Home) {
    val navController = rememberNavController()
    val actions = remember(navController) { Actions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Destinations.Home) {
            HomeScreen(onAboutClick = actions.openAbout)
        }
        composable(Destinations.About) {
            About(onUpPress = actions.onUpPress)
        }
    }
}

internal data class Actions(val navController: NavHostController) {
    val openAbout: () -> Unit = { navController.navigate(Destinations.About) }
    val onUpPress: () -> Unit = { navController.navigateUp() }
}
