package prom.application.mynotes.db

import prom.application.mynotes.db.entity.NotesEntity
import prom.application.mynotes.model.NotesModel

interface NotesRep {

    suspend fun insertNotes(notes: NotesEntity)

    suspend fun getNotes() : List<NotesModel>

    suspend fun updateNotes(notes: NotesEntity)

    suspend fun deleteNotes(index : Int)

    suspend fun restoreNote()

    suspend fun toggle(notes: NotesEntity)

    suspend fun findNotesWith(query: String)
}