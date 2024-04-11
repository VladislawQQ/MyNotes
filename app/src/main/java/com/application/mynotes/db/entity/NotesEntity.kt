package com.application.mynotes.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.application.mynotes.model.NotesModel

@Entity(tableName = "notes")
data class NotesEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    val title : String = "",
    val notes : String = ""
) {
    fun toNotesModel() : NotesModel {
        return NotesModel(
            id = id,
            title = title,
            notes = notes,
        )
    }
}