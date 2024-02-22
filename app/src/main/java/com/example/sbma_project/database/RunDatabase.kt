package com.example.sbma_project.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Run::class],
    version = 1
)
abstract class RunDatabase: RoomDatabase() {

    abstract val dao: RunDao
}