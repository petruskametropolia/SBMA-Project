package com.example.sbma_project

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen (
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object Home : BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )
    data object History : BottomBarScreen(
        route = "history",
        title = "History",
        icon = Icons.Default.List
    )
    data object Info : BottomBarScreen(
        route = "info",
        title = "Info",
        icon = Icons.Default.Info
    )
}
