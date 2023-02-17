package com.francis.note.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "note")
data class Note(
    @PrimaryKey(autoGenerate = true)
//    @get:Exclude
    var id: Int = 0,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "body")
    var body: String
//    @ColumnInfo(name = "last_updated")
//    val lastUpdate: Date?,

//    @ColumnInfo(name = "announcements")
//    var announcement: Boolean
)
