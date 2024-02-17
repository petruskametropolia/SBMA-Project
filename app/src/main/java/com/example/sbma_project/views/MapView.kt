package com.example.sbma_project.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapView(modifier: Modifier) {
    val mapProperties = MapProperties(
        isMyLocationEnabled = true
    )

    val cameraPositionState = rememberCameraPositionState()

    Box(modifier = modifier) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            properties = mapProperties,
            cameraPositionState = cameraPositionState
        ){
            Polyline(points = listOf(
                LatLng(60.23634, 24.81781),
                LatLng(60.23634, 24.82781),
                LatLng(60.20634, 24.81781),
                ),
                color = MaterialTheme.colorScheme.primary,
                width = 20f,
            )
        }
    }


}
