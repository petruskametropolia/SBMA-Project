package com.example.sbma_project.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.maps.android.compose.GoogleMap

@Composable
fun MapView(){
    Box(modifier = Modifier.fillMaxSize()){
        GoogleMap {

        }
    }

}