package com.example.sbma_project.views

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    Text(text = "Timer ID: ${timer.id}, Duration: ${timer.durationInMillis}s, path array size : ${timer.routePath?.size}")
}