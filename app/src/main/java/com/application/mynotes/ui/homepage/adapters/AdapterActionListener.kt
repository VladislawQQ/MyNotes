package com.application.mynotes.ui.homepage.adapters

import android.view.View
import com.application.mynotes.model.NotesModel

interface AdapterActionListener {

    fun onNotesClick(notes: NotesModel, transitionNames: Pair<View, String>)

    fun onNotesLongClick(notes: NotesModel)
}