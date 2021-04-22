package com.example.homework4

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.w3c.dom.Text
import java.sql.Date
import java.sql.RowId

@Entity(tableName = "dream_table")
class Dream(@PrimaryKey(autoGenerate = true) val id:Int,
            @ColumnInfo(name="date") val date:String, // added extra
            @ColumnInfo(name="title") val title:String,
            @ColumnInfo(name="description") val description:String,
            @ColumnInfo(name="interpretation") val interpretation:String,
            @ColumnInfo(name="emotion") val emotion:String)
