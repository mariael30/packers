package org.d3if3137.packers.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.d3if3137.packers.DetailList
import org.d3if3137.packers.ListScreen
import org.d3if3137.packers.Screen
import org.d3if3137.packers.Start

@Composable
fun SetupNavGraph(navController : NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            Start(navController)
        }
        composable(Screen.ListBarang.route) {
            ListScreen(navController)
        }
        composable(Screen.FormBarang.route) {
            DetailList(navController)
        }
    }
}
