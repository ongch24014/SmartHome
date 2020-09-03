package com.example.potensituitionapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DoorUnlockDao {
    @Insert
    fun insert(door: DoorUnlock)

    @Update
    fun update(door: DoorUnlock)

    @Query("SELECT * from doorunlock_table WHERE num = :key")
    fun getFromKey(key: Long): DoorUnlock?

    @Query("SELECT * from doorunlock_table WHERE doorunlock_ID = :key")
    fun get(key: String): DoorUnlock?

    @Query("SELECT * from doorunlock_table WHERE fulldate = :key")
    fun getDate(key: String): DoorUnlock?

    @Query("SELECT * FROM doorunlock_table ORDER BY doorunlock_ID DESC")
    fun getDoorsLive(): LiveData<List<DoorUnlock>>

    @Query("SELECT * FROM doorunlock_table ORDER BY doorunlock_ID DESC")
    fun getDoors(): List<DoorUnlock>

    @Query("SELECT COUNT(*) FROM doorunlock_table")
    fun getCount(): Int

}