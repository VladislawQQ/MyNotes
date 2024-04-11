package com.application.mynotes.ui.homepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.mynotes.db.NotesDBImpl
import com.application.mynotes.model.NotesModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FragmentHomePageViewModel @Inject constructor(
    private val notesDBImpl: NotesDBImpl
) : ViewModel() {

    val notesStateFlow = notesDBImpl.notesListStateFlow

    fun deleteNote(notes : NotesModel) {
        viewModelScope.launch {
            notesDBImpl.deleteNotes(notes.toNotesEntity())
        }
    }

    fun toggle(notes: NotesModel) {
        notes.isSelected = !notes.isSelected
    }

    fun findNotesWith(query: String) {
        viewModelScope.launch {
            notesDBImpl.findNotesWith(query)
        }
    }

    fun getNotes() {
        viewModelScope.launch {
            notesDBImpl.getNotes()
        }
    }
}