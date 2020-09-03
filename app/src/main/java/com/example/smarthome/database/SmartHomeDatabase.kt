package com.example.smarthome.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.potensituitionapp.database.*

@Database(entities = arrayOf(Door::class, Lights::class, Temperature::class, DoorLock::class, DoorUnlock::class), version = 4,exportSchema = false)
abstract class SmartHomeDatabase : RoomDatabase() {

    abstract val doorDatabaseDao: DoorDao
    abstract val doorLockDatabaseDao: DoorLockDao
    abstract val doorUnlockDatabaseDao: DoorUnlockDao

    abstract val lightsDatabaseDao: LightsDao
    abstract val temperatureDatabaseDao: TemperatureDao


    companion object {
        @Volatile
        private var INSTANCE: SmartHomeDatabase? = null

        fun getInstance(context: Context): SmartHomeDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SmartHomeDatabase::class.java,
                        "smarthome_database")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }


}