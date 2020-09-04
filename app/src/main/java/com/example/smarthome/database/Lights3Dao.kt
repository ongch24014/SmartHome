package com.example.potensituitionapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface Lights3Dao {
    @Insert
    fun insert(lights: Lights3)

    @Update
    fun update(lights: Lights3)

    @Query("SELECT * from lights3_table WHERE num = :key")
    fun getFromKey(key: Long): Lights3?

    @Query("SELECT * from lights3_table WHERE lights3_ID = :key")
    fun get(key: String): Lights3?

    @Query("SELECT * from lights3_table WHERE fulldate = :key")
    fun getDate(key: String): Lights3?

    @Query("SELECT * FROM lights3_table ORDER BY lights3_ID DESC")
    fun getLightsLive(): LiveData<List<Lights3>>

    @Query("SELECT * FROM lights3_table ORDER BY lights3_ID DESC")
    fun getLights(): List<Lights3>

    @Query("SELECT COUNT(*) FROM lights3_table")
    fun getCount(): Int
}