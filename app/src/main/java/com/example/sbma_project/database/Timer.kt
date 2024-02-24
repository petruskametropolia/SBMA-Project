package com.example.sbma_project.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity(tableName = "timers")
data class Timer(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val durationInMillis: Long = 0L,
)

@Dao
interface TimerDao {
    @Insert
    suspend fun insertTimer(timer: Timer): Long

    @Query("SELECT * FROM timers")
    fun getAllTimers(): LiveData<List<Timer>>

}