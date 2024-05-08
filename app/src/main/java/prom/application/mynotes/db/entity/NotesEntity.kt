package prom.application.mynotes.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import prom.application.mynotes.model.NotesModel

@Entity(tableName = "notes")
data class NotesEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    val title : String = "",
    val notes : String = "",
    val isSelected : Boolean = false
) {
    fun toNotesModel() : NotesModel {
        return NotesModel(
            id = id,
            title = title,
            notes = notes,
            isSelected = isSelected
        )
    }
}