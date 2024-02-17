package com.example.sbma_project

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sbma_project.views.Home

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(isConnected: Boolean) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            BottomNavGraph(navController = navController, isConnected = isConnected)
        }
    }
}





@Composable
fun BottomBar(navController: NavHostController){
val views = listOf(
    BottomBarScreen.Home,
    BottomBarScreen.History,
    BottomBarScreen.Info,
)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        views.forEach { view ->
                AddItem(view = view, currentDestination = currentDestination, navController = navController)
        }
    }
}

@Composable
fun RowScope.AddItem(
    view: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
){
    NavigationBarItem(
        label = {
            Text(text = view.title)
        },
        icon = {
        Icon(imageVector = view.icon, contentDescription = "Navigation icon")
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == view.route
        } == true,
        onClick = {
            navController.navigate(view.route)
        }
    )
}