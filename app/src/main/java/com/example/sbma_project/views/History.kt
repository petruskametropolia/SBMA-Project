package com.example.sbma_project.views

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import com.example.sbma_project.database.Timer
import com.example.sbma_project.repository.TimerViewModel

@Composable
fun History(timerViewModel: TimerViewModel) {
    val timersState = remember { mutableStateOf<List<Timer>>(emptyList()) }

    LaunchedEffect(key1 = timerViewModel.timers) {
        timerViewModel.timers.observeForever { timers ->
            timersState.value = timers
        }
    }
    LazyColumn {
        items(timersState.value) { timer ->
            TimerItem(timer = timer)
        }
    }
}


@Composable
fun TimerItem(timer: Timer) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "ID: ${timer.id}")
            Text(text = "Duration: ${timer.durationInMillis}s")
            Text(text = "route LatLng size: ${timer.routePath?.size}")
        }
    }
}