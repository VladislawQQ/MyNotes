package com.application.mynotes.model

import android.os.Parcelable
import com.application.mynotes.db.entity.NotesEntity
import kotlinx.parcelize.Parcelize

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
        )
    }
}