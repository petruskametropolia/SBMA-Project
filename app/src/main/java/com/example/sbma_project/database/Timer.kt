package com.example.sbma_project.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Entity
data class Timer(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val durationInMills : Long = 0L,
)

@Dao
interface TimerDao{

    @Insert
    suspend fun insertTimer(timer: Timer)

    @Query("SELECT * FROM timer")
    fun getAllTimers(): LiveData<List<Timer>>

}