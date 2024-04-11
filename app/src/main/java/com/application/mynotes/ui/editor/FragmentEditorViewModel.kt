package com.application.mynotes.ui.editor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.mynotes.db.NotesDBImpl
import com.application.mynotes.model.NotesModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FragmentEditorViewModel @Inject constructor(
    private val notesDBImpl: NotesDBImpl
) : ViewModel() {

    fun insertNote(notes : NotesModel) {
        viewModelScope.launch {
            notesDBImpl.insertNotes(notes.toNotesEntity())
        }
    }

    fun updateNotes(notes : NotesModel) {
        viewModelScope.launch {
            notesDBImpl.updateNotes(notes.toNotesEntity())
        }
    }
}