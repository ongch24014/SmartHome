package com.example.potensituitionapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface Lights2Dao {
    @Insert
    fun insert(lights: Lights2)

    @Update
    fun update(lights: Lights2)

    @Query("SELECT * from lights2_table WHERE num = :key")
    fun getFromKey(key: Long): Lights2?

    @Query("SELECT * from lights2_table WHERE lights2_ID = :key")
    fun get(key: String): Lights2?

    @Query("SELECT * from lights2_table WHERE fulldate = :key")
    fun getDate(key: String): Lights2?

    @Query("SELECT * FROM lights2_table ORDER BY lights2_ID DESC")
    fun getLightsLive(): LiveData<List<Lights2>>

    @Query("SELECT * FROM lights2_table ORDER BY lights2_ID DESC")
    fun getLights(): List<Lights2>

    @Query("SELECT COUNT(*) FROM lights2_table")
    fun getCount(): Int
}