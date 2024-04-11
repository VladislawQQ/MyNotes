package com.application.mynotes.db

import android.util.Log
import com.application.mynotes.db.entity.NotesEntity
import com.application.mynotes.model.NotesModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class NotesDBImpl @Inject constructor(
    private val dao: DAO
) : NotesRep {

    private val _notesListStateFlow = MutableStateFlow(emptyList<NotesModel>())
    val notesListStateFlow = _notesListStateFlow.asStateFlow()

    init {
        runBlocking {
            _notesListStateFlow.value = dao.getNotes().map {
                it.toNotesModel()
            }
        }
    }

    override suspend fun getNotes(): List<NotesModel> {
        val newList = dao.getNotes().map {
            it.toNotesModel()
        }
        _notesListStateFlow.value = newList

        return _notesListStateFlow.value
    }

    override suspend fun insertNotes(notes: NotesEntity) {
        dao.insertNotes(notes)
        _notesListStateFlow.value = getNotes()
    }

    override suspend fun updateNotes(notes: NotesEntity) {
        dao.updateNotes(notes.id, notes.title, notes.notes)
        _notesListStateFlow.value = getNotes()
    }

    override suspend fun deleteNotes(notes: NotesEntity) {
        dao.deleteNotes(notes)
        _notesListStateFlow.value = getNotes()
    }

    override suspend fun findNotesWith(query: String) {
        val newList = dao.findNotesWith(query).map {
            it.toNotesModel()
        }
        newList.map { Log.d("List", "List : ${it.title}") }
        _notesListStateFlow.value = newList
    }
}