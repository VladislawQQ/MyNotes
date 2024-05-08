package prom.application.mynotes.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import prom.application.mynotes.db.entity.NotesEntity

@Parcelize
data class NotesModel(
    var id : Int = 0,
    val title : String = "",
    val notes : String = "",
    var isSelected: Boolean = false
) : Parcelable {
    fun toNotesEntity() : NotesEntity {
        return NotesEntity(
            id = id,
            title = title,
            notes = notes,
            isSelected = isSelected
        )
    }
}