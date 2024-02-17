package com.example.sbma_project

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sbma_project.views.History
import com.example.sbma_project.views.Home
import com.example.sbma_project.views.Info

@Composable
fun BottomNavGraph(navController: NavHostController, isConnected: Boolean) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route,
    ) {
        composable(route = BottomBarScreen.Home.route) {
            Home(isConnected = isConnected )
        }
        composable(route = BottomBarScreen.History.route) {
            History()
        }
        composable(route = BottomBarScreen.Info.route) {
            Info()
        }
    }
}