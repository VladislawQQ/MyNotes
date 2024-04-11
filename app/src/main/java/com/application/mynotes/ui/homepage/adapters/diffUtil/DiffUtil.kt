package com.application.mynotes.ui.homepage.adapters.diffUtil

import androidx.recyclerview.widget.DiffUtil
import com.application.mynotes.model.NotesModel

class DiffUtil : DiffUtil.ItemCallback<NotesModel>() {

    override fun areItemsTheSame(oldItem: NotesModel, newItem: NotesModel):
            Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: NotesModel, newItem: NotesModel):
            Boolean = oldItem.id == newItem.id
}