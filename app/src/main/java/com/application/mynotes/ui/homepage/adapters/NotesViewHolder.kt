package com.application.mynotes.ui.homepage.adapters

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.RecyclerView
import com.application.mynotes.R
import com.application.mynotes.databinding.ItemNoteBinding
import com.application.mynotes.model.NotesModel

class NotesViewHolder(
    private val binding: ItemNoteBinding,
    private val listener: AdapterActionListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(notes: NotesModel) {
        with(binding) {
            textViewTitleText.text = notes.title
        }

        // Listeners
        with(binding) {
            cardContainer.setOnClickListener {
                onItemClick(notes)
            }

            cardContainer.setOnLongClickListener {
                listener.onNotesLongClick(notes)
                onNotesLongClick(notes)
                true
            }
        }
    }

    private fun onNotesLongClick(notes: NotesModel) {
        with(binding) {
            if (notes.isSelected) {
                textViewTitleText.visibility = GONE
                imageViewDeleteBucket.visibility = VISIBLE
                cardContainer.background.setTint(cardContainer.context.getColor(R.color.item_note_background_delete_color))
            } else {
                textViewTitleText.visibility = VISIBLE
                imageViewDeleteBucket.visibility = GONE
                cardContainer.background.setTint(cardContainer.context.getColor(R.color.item_note_background_color))
            }
        }
    }

    private fun onItemClick(notes: NotesModel) {
        with(binding) {
            listener.onNotesClick(
                notes,
                setTransitionName(
                    textViewTitleText, "title"
                )
            )
        }
    }

    private fun setTransitionName(view: View, transitionName: String): Pair<View, String> {
        view.transitionName = transitionName
        return view to transitionName
    }
}