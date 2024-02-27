package com.example.sbma_project.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.TypeConverter
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "timers")
data class Timer(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val durationInMillis: Long = 0L,
    val routePath : List<LatLng>?,
)

@Dao
interface TimerDao {
    @Insert
    suspend fun insertTimer(timer: Timer)

    @Query("SELECT * FROM timers")
    fun getAllTimers(): LiveData<List<Timer>>

}

object Converters {
    @TypeConverter
    @JvmStatic
    fun fromLatLngList(value: List<LatLng>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    @JvmStatic
    fun toLatLngList(value: String?): List<LatLng>? {
        val listType = object : TypeToken<List<LatLng>>() {}.type
        return Gson().fromJson(value, listType)
    }
}