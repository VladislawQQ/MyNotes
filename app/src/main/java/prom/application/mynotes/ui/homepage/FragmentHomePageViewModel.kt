package prom.application.mynotes.ui.homepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import prom.application.mynotes.db.NotesDBImpl
import prom.application.mynotes.model.NotesModel
import javax.inject.Inject

@HiltViewModel
class FragmentHomePageViewModel @Inject constructor(
    private val notesDBImpl: NotesDBImpl
) : ViewModel() {

    val notesStateFlow = notesDBImpl.notesListStateFlow

    fun deleteNote(index : Int) {
        viewModelScope.launch {
            notesDBImpl.deleteNotes(index)
        }
    }

    fun toggle(notes: NotesModel) {
        notes.isSelected = !notes.isSelected
        viewModelScope.launch {
            notesDBImpl.toggle(notes.toNotesEntity())
        }
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

    fun restoreNote() {
        viewModelScope.launch {
            notesDBImpl.restoreNote()
        }
    }
}