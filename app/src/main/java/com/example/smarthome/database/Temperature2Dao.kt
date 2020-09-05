package com.example.potensituitionapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface Temperature2Dao {
    @Insert
    fun insert(temperature: Temperature2)

    @Update
    fun update(temperature: Temperature2)

    @Query("SELECT * from temperature2_table WHERE num = :key")
    fun getFromKey(key: Long): Temperature2?

    @Query("SELECT * from temperature2_table WHERE temperature_ID = :key")
    fun get(key: String): Temperature2?

    @Query("SELECT * from temperature2_table WHERE fulldate = :key")
    fun getDate(key: String): Temperature2?

    @Query("SELECT * FROM temperature2_table ORDER BY temperature_ID DESC")
    fun getTemperatureLive(): LiveData<List<Temperature2>>

    @Query("SELECT * FROM temperature2_table ORDER BY temperature_ID DESC")
    fun getTemperature(): List<Temperature2>

    @Query("SELECT COUNT(*) FROM temperature2_table")
    fun getCount(): Int

    @Query("SELECT AVG(temperatureReading) FROM temperature2_table ")
    fun getTempAvg(): Double
}