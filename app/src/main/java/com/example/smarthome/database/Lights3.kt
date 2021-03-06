package com.example.potensituitionapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lights3_table")
data class Lights3 (
    @PrimaryKey(autoGenerate = true)
    var num: Long = 0L,

    @ColumnInfo(name = "lights3_ID")
    var lights3ID: String = "",

    @ColumnInfo(name = "year")
    var year: String = "",

    @ColumnInfo(name = "month")
    var month: String ="",

    @ColumnInfo(name = "day")
    var day: String = "",

    @ColumnInfo(name = "time")
    var time: String = "",

    @ColumnInfo(name = "selected_time")
    var selectedTime: String = "",

    @ColumnInfo(name = "option")
    var option: String = "",

    @ColumnInfo(name = "fulldate")
    var fulldate: String = ""
)

