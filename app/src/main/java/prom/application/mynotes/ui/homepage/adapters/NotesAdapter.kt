package prom.application.mynotes.ui.homepage.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import prom.application.mynotes.databinding.ItemNoteBinding
import prom.application.mynotes.model.NotesModel
import prom.application.mynotes.ui.homepage.adapters.diffUtil.DiffUtil
import prom.application.mynotes.ui.homepage.adapters.listeners.AdapterActionListener

class NotesAdapter(
    private val listener : AdapterActionListener
) : ListAdapter<NotesModel, NotesAdapter.NotesViewHolder>(DiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemNoteBinding.inflate(inflater, parent, false)
        return NotesViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class NotesViewHolder(
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
}