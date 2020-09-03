package com.example.potensituitionapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TemperatureDao {
    @Insert
    fun insert(temperature: Temperature)

    @Update
    fun update(temperature: Temperature)

    @Query("SELECT * from temperature_table WHERE num = :key")
    fun getFromKey(key: Long): Temperature?

    @Query("SELECT * from temperature_table WHERE temperature_ID = :key")
    fun get(key: String): Temperature?

    @Query("SELECT * from temperature_table WHERE fulldate = :key")
    fun getDate(key: String): Temperature?

    @Query("SELECT * FROM temperature_table ORDER BY temperature_ID DESC")
    fun getTemperatureLive(): LiveData<List<Temperature>>

    @Query("SELECT * FROM temperature_table ORDER BY temperature_ID DESC")
    fun getTemperature(): List<Temperature>

    @Query("SELECT COUNT(*) FROM lights_table")
    fun getCount(): Int
}