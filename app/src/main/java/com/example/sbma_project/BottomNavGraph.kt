package com.example.sbma_project

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sbma_project.views.History
import com.example.sbma_project.views.Home
import com.example.sbma_project.views.Info

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            Home()
        }
        composable(route = BottomBarScreen.History.route) {
            History()
        }
        composable(route = BottomBarScreen.Info.route) {
            Info()
        }
    }
}