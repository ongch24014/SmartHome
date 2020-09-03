package com.example.potensituitionapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "door_table")
data class Door (
    @PrimaryKey(autoGenerate = true)
    var num: Long = 0L,

    @ColumnInfo(name = "door_ID")
    var doorID: String = "",

    @ColumnInfo(name = "year")
    var year: String = "",

    @ColumnInfo(name = "month")
    var month: String ="",

    @ColumnInfo(name = "day")
    var day: String = "",

    @ColumnInfo(name = "time")
    var time: String = "",

    @ColumnInfo(name = "fulldate")
    var fulldate: String = ""
)