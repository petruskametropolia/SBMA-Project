package com.example.sbma_project.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Run::class], [Timer::class],
    version = 2
)
abstract class RunDatabase: RoomDatabase() {

    abstract val dao: RunDao

    abstract fun timerDao() :TimerDao
}