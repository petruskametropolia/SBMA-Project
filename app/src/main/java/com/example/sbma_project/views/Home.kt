package com.example.sbma_project.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sbma_project.uiComponents.RunCard

@Composable
fun Home(isConnected: Boolean) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (isConnected) {
            Box(modifier = Modifier.fillMaxSize()) {
                Column {
                    MapView(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.7f)
                    )
                    RunCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(1f)
                            .padding(10.dp)
                    )
                }

            }
        } else {
            NoInternetScreen()
        }
    }
}

