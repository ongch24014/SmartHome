package com.example.smarthome.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.potensituitionapp.database.*

@Database(entities = arrayOf(Door::class, Lights::class, Temperature::class, Temperature2::class, DoorLock::class, DoorUnlock::class,
    Lights1::class,Lights2::class,Lights3::class), version = 6,exportSchema = false)
abstract class SmartHomeDatabase : RoomDatabase() {

    abstract val doorDatabaseDao: DoorDao
    abstract val doorLockDatabaseDao: DoorLockDao
    abstract val doorUnlockDatabaseDao: DoorUnlockDao

    abstract val lightsDatabaseDao: LightsDao
    abstract val lights1DatabaseDao: Lights1Dao
    abstract val lights2DatabaseDao: Lights2Dao
    abstract val lights3DatabaseDao: Lights3Dao
    abstract val temperatureDatabaseDao: TemperatureDao
    abstract val temperature2DatabaseDao: Temperature2Dao


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