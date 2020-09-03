package com.example.potensituitionapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DoorLockDao {
    @Insert
    fun insert(door: DoorLock)

    @Update
    fun update(door: DoorLock)

    @Query("SELECT * from doorlock_table WHERE num = :key")
    fun getFromKey(key: Long): DoorLock?

    @Query("SELECT * from doorlock_table WHERE doorlock_ID = :key")
    fun get(key: String): DoorLock?

    @Query("SELECT * from doorlock_table WHERE fulldate = :key")
    fun getDate(key: String): DoorLock?

    @Query("SELECT * FROM doorlock_table ORDER BY doorlock_ID DESC")
    fun getDoorsLive(): LiveData<List<DoorLock>>

    @Query("SELECT * FROM doorlock_table ORDER BY doorlock_ID DESC")
    fun getDoors(): List<DoorLock>

    @Query("SELECT COUNT(*) FROM doorlock_table")
    fun getCount(): Int

}