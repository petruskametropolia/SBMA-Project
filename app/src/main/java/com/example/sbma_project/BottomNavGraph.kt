package com.example.sbma_project

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sbma_project.views.History
import com.example.sbma_project.views.Home
import com.example.sbma_project.views.Info
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    locationPermissionState :String,
    currentPosition: LatLng? = null,
    cameraState: CameraPositionState? = null,
    pathPoints: List<LatLng>? = null,
    settingsActionListener: SettingsActionListener,
    isConnected: Boolean
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route,
    ) {
        composable(route = BottomBarScreen.Home.route) {
            Home(
                locationPermissionState = locationPermissionState,
                currentPosition= currentPosition, cameraState = cameraState,
                pathPoints = pathPoints,
                settingsActionListener = settingsActionListener,
                isConnected = isConnected)
        }
        composable(route = BottomBarScreen.History.route) {
            History()
        }
        composable(route = BottomBarScreen.Info.route) {
            Info()
        }
    }
}