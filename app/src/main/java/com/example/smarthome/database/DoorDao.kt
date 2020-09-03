package com.example.potensituitionapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DoorDao {
    @Insert
    fun insert(door: Door)

    @Update
    fun update(door: Door)

    @Query("SELECT * from door_table WHERE num = :key")
    fun getFromKey(key: Long): Door?

    @Query("SELECT * from door_table WHERE door_ID = :key")
    fun get(key: String): Door?

    @Query("SELECT * from door_table WHERE fulldate = :key")
    fun getDate(key: String): Door?

    @Query("SELECT * FROM door_table ORDER BY door_ID DESC")
    fun getDoorsLive(): LiveData<List<Door>>

    @Query("SELECT * FROM door_table ORDER BY door_ID DESC")
    fun getDoors(): List<Door>

    @Query("SELECT COUNT(*) FROM door_table")
    fun getCount(): Int

}