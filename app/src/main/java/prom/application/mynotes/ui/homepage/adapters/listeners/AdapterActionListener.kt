package prom.application.mynotes.ui.homepage.adapters.listeners

import android.view.View
import prom.application.mynotes.model.NotesModel

interface AdapterActionListener {

    fun onNotesClick(notes: NotesModel, transitionNames: Pair<View, String>)
}