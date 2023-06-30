package com.project.note_list_alexandre

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notesTable")

class Note(
    @ColumnInfo(name = "title") val noteTitle: String,
    @ColumnInfo(name = "description") val noteDescription: String,
    @ColumnInfo(name = "timestamp") val timeStamp: String,
    @ColumnInfo(name = "isChecked") var isChecked: Boolean = false

) {

    @PrimaryKey(autoGenerate = true)
    var id = 0
}