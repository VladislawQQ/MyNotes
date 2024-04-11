package com.application.mynotes.ui.editor

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.application.mynotes.R
import com.application.mynotes.databinding.FragmentEditorBinding
import com.application.mynotes.model.NotesModel
import com.application.mynotes.ui.base.BaseFragment
import com.application.mynotes.ui.homepage.FragmentHomePageArgs

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentEditor : BaseFragment<FragmentEditorBinding>(FragmentEditorBinding::inflate) {

    private val args : FragmentHomePageArgs by navArgs()
    private val viewModel : FragmentEditorViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setStartedView(args.notes)
        bindNotesToTextViews(args.notes)
        setListeners()
        attachAnimation()
    }

    private fun bindNotesToTextViews(notes: NotesModel?) {
        with(binding) {
            if (notes != null) {
                textViewTitleText.text = notes.title
                textViewNoteText.text = notes.notes
            }
        }
    }

    private fun bindNotesToEditTexts(notes: NotesModel?) {
        with(binding) {
            if (notes != null) {
                editTextTitle.setText(notes.title)
                editTextNote.setText(notes.notes)
            }
        }
    }

    private fun setStartedView(notes: NotesModel?) {
        with(binding) {
            editTextTitle.visibility = if (notes == null) VISIBLE else GONE
            editTextNote.visibility = if (notes == null) VISIBLE else GONE
            textViewTitleText.visibility = if (notes == null) GONE else VISIBLE
            textViewNoteText.visibility = if (notes == null) GONE else VISIBLE
            buttonVisibilityMode.visibility = if (notes == null) VISIBLE else GONE
            buttonSave.visibility = if (notes == null) VISIBLE else GONE
            buttonEditMode.visibility = if (notes == null) GONE else VISIBLE
        }
    }

    private fun setListeners() {
        with(binding) {

            buttonVisibilityMode.setOnClickListener {
                swapView()
                bindNotesToTextViews(
                    NotesModel(
                    title = editTextTitle.text.toString(),
                    notes = editTextNote.text.toString()
                )
                )
            }

            buttonSave.setOnClickListener {
                if (args.notes == null) {
                    viewModel.insertNote(
                        NotesModel(
                            title = editTextTitle.text.toString(),
                            notes = editTextNote.text.toString()
                        )
                    )
                } else {
                    viewModel.updateNotes(
                        NotesModel(
                            args.notes!!.id,
                            title = editTextTitle.text.toString(),
                            notes = editTextNote.text.toString(),
                        )
                    )
                }

                val action = FragmentEditorDirections.actionFragmentEditorToFragmentHomePage(
                    NotesModel(
                        (args.notes?.id ?: 0),
                        title = editTextTitle.text.toString(),
                        notes = editTextNote.text.toString()
                    )
                )

                navController.navigate(action)
            }

            buttonBack.setOnClickListener {
                navigateUp()
            }

            buttonEditMode.setOnClickListener {
                swapView()
                bindNotesToEditTexts(
                    NotesModel(
                        title = textViewTitleText.text.toString(),
                        notes = textViewNoteText.text.toString()
                    )
                )
            }

            backPressedListener()
        }
    }

    private fun swapView() {
        with(binding) {
            editTextTitle.visibility = if (editTextTitle.visibility == GONE) VISIBLE else GONE
            editTextNote.visibility = if (editTextNote.visibility == GONE) VISIBLE else GONE
            textViewTitleText.visibility = if (textViewTitleText.visibility == VISIBLE) GONE else VISIBLE
            textViewNoteText.visibility = if (textViewNoteText.visibility == VISIBLE) GONE else VISIBLE
            buttonVisibilityMode.visibility = if (buttonVisibilityMode.visibility == GONE) VISIBLE else GONE
            buttonSave.visibility = if (buttonSave.visibility == GONE) VISIBLE else GONE
            buttonEditMode.visibility = if (buttonEditMode.visibility == VISIBLE) GONE else VISIBLE
        }
    }


    private fun attachAnimation() {
        with(binding) {
            editTextTitle.transitionName = "title"
        }

        val animation = TransitionInflater.from(requireContext()).inflateTransition(R.transition.transition)

        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation
    }

    private fun backPressedListener() {
        val callBack = object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                navigateUp()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callBack)
    }

    private fun navigateUp() {
        val action = FragmentEditorDirections.actionFragmentEditorToFragmentHomePage(null)
        navController.navigate(action)
    }
}