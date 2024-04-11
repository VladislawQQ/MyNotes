package com.application.mynotes.db

import com.application.mynotes.db.entity.NotesEntity
import com.application.mynotes.model.NotesModel

interface NotesRep {

    suspend fun insertNotes(notes: NotesEntity)

    suspend fun getNotes() : List<NotesModel>

    suspend fun updateNotes(notes: NotesEntity)

    suspend fun deleteNotes(notes: NotesEntity)

    suspend fun findNotesWith(query: String)
}