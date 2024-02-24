package com.example.sbma_project.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import com.example.sbma_project.database.Timer
import com.example.sbma_project.database.TimerDao
import dagger.hilt.InstallIn
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


class TimerRepository @Inject constructor(private val timerDao: TimerDao) {

    suspend fun insertTimer(timer: Timer) {
        timerDao.insertTimer(timer)
    }

    fun getActiveTimerFlow(): LiveData<List<Timer>> {
        return timerDao.getAllTimers()
    }
}

@HiltViewModel
class TimerViewModel @Inject constructor(private val timerRepository: TimerRepository){

}


