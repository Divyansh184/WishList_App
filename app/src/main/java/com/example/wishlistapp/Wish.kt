package com.example.wishlistapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "wish-table")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    val id:Int =0,
    @ColumnInfo(name = "Wish-title")
    val title:String="",
    @ColumnInfo(name = "Wish-desc")
    val desc:String=""
)
