package com.example.sbma_project.repository

import androidx.room.Dao
import com.example.sbma_project.database.TimerDao
import dagger.hilt.InstallIn
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


class TimerRepository @Inject constructor(timerDao: TimerDao) {
}

@HiltViewModel
class TimerViewModel @Inject constructor(timerRepository: TimerRepository){

}