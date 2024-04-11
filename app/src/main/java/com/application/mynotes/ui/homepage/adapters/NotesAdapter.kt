package com.application.mynotes.ui.homepage.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.application.mynotes.databinding.ItemNoteBinding
import com.application.mynotes.model.NotesModel
import com.application.mynotes.ui.homepage.adapters.diffUtil.DiffUtil

class NotesAdapter(
    private val listener : AdapterActionListener
) : ListAdapter<NotesModel, NotesViewHolder>(DiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemNoteBinding.inflate(inflater, parent, false)
        return NotesViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}