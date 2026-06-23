package com.hitech.pickit.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberPickItAppState(
    navController: NavHostController = rememberNavController()
) = remember(navController) {
    PickItAppState(navController)
}

@Stable
class PickItAppState(val navController: NavHostController) {

    val bottomBarTabs = BottomNavItem.entries.toTypedArray()
    private val bottomBarRoutes = bottomBarTabs.map { it.route }

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentRoute: String?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination?.route


    val shouldShowBottomBar: Boolean
        @Composable get() = currentDestination?.route in bottomBarRoutes


    fun navigateToBottomBarRoute(route: String) {
        val currentRoute = navController.currentDestination?.route
        if (route != currentRoute) {
            navController.navigate(route) {
                launchSingleTop = true
                restoreState = true
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
            }
        }
    }


    private val NavGraph.startDestination: NavDestination?
        get() = findNode(startDestinationId)


    private tailrec fun NavGraph.findStartDestination(): NavDestination {
        val startDest = startDestination
        return if (startDest is NavGraph) startDest.findStartDestination() else startDest!!
    }
}