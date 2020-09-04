package com.example.potensituitionapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface Lights1Dao {
    @Insert
    fun insert(lights: Lights1)

    @Update
    fun update(lights: Lights1)

    @Query("SELECT * from lights1_table WHERE num = :key")
    fun getFromKey(key: Long): Lights1?

    @Query("SELECT * from lights1_table WHERE lights1_ID = :key")
    fun get(key: String): Lights1?

    @Query("SELECT * from lights1_table WHERE fulldate = :key")
    fun getDate(key: String): Lights1?

    @Query("SELECT * FROM lights1_table ORDER BY lights1_ID DESC")
    fun getLightsLive(): LiveData<List<Lights1>>

    @Query("SELECT * FROM lights1_table ORDER BY lights1_ID DESC")
    fun getLights(): List<Lights1>

    @Query("SELECT COUNT(*) FROM lights1_table")
    fun getCount(): Int
}