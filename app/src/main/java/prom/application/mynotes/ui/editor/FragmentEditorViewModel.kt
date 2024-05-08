package prom.application.mynotes.ui.editor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import prom.application.mynotes.db.NotesDBImpl
import prom.application.mynotes.model.NotesModel
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