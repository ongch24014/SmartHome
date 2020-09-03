package com.example.potensituitionapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface LightsDao {
    @Insert
    fun insert(lights: Lights)

    @Update
    fun update(lights: Lights)

    @Query("SELECT * from lights_table WHERE num = :key")
    fun getFromKey(key: Long): Lights?

    @Query("SELECT * from lights_table WHERE lights_ID = :key")
    fun get(key: String): Lights?

    @Query("SELECT * from lights_table WHERE fulldate = :key")
    fun getDate(key: String): Lights?

    @Query("SELECT * FROM lights_table ORDER BY lights_ID DESC")
    fun getLightsLive(): LiveData<List<Lights>>

    @Query("SELECT * FROM lights_table ORDER BY lights_ID DESC")
    fun getLights(): List<Lights>

    @Query("SELECT COUNT(*) FROM lights_table")
    fun getCount(): Int
}