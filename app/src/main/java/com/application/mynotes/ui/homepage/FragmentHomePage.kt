package com.application.mynotes.ui.homepage

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.application.mynotes.databinding.FragmentHomePageBinding
import com.application.mynotes.model.NotesModel
import com.application.mynotes.ui.base.BaseFragment
import com.application.mynotes.ui.homepage.adapters.AdapterActionListener
import com.application.mynotes.ui.homepage.adapters.NotesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentHomePage : BaseFragment<FragmentHomePageBinding>(FragmentHomePageBinding::inflate)  {

    private val viewModel : FragmentHomePageViewModel by viewModels()

    private val homePageAdapter by lazy {
        NotesAdapter(listener = object :
            AdapterActionListener {
            override fun onNotesClick(notes: NotesModel, transitionNames: Pair<View, String>) {
                if (notes.isSelected) {
                    viewModel.deleteNote(notes)
                } else  {
                    startEditor(notes)
                    Log.d("notes", "Notes : ${notes.title} ${notes.notes}")
                }
            }

            override fun onNotesLongClick(notes: NotesModel) {
                viewModel.toggle(notes)
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
        observeViewModel()
        backPressedListener()
        setListeners()
    }

    private fun setListeners() {
        binding.floatingButtonAddNote.setOnClickListener {
            startEditor()
        }

        binding.searchViewSearchNotes.setOnFocusChangeListener { view, isFocus ->
            with(binding) {
                if (isFocus) {
                    imageViewHomePage.visibility = GONE
                    textViewCreateFirstNote.visibility = GONE
                }
            }
        }

        binding.searchViewSearchNotes.setCustomViewListener(object : SearchViewActionListener {

            override fun onTextChange(newText : String) {
                viewModel.findNotesWith(newText)
            }

            override fun onTextSubmit(query : String) {
                viewModel.findNotesWith(query)
            }

            override fun onClickListener() {
                binding.textViewNotes.visibility =
                    if (binding.textViewNotes.visibility == GONE)
                        VISIBLE else GONE
            }

            override fun getNotesList() {
                viewModel.getNotes()
            }
        })
    }

    private fun setRecyclerView() {
        val recyclerLayoutManager = LinearLayoutManager(activity)

        with(binding.recyclerViewNotes) {
            layoutManager = recyclerLayoutManager
            adapter = homePageAdapter
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.notesStateFlow.collect { notes ->
                    homePageAdapter.submitList(notes)
                    setView(notes)
                }
            }
        }
    }

    private fun setView(notes: List<NotesModel>) {
        with(binding) {
            if (notes.isEmpty()) {
                imageViewHomePage.visibility = VISIBLE
                textViewCreateFirstNote.visibility = VISIBLE
                recyclerViewNotes.visibility = GONE
            } else {
                imageViewHomePage.visibility = GONE
                textViewCreateFirstNote.visibility = GONE
                recyclerViewNotes.visibility = VISIBLE
            }
        }
    }

    private fun startEditor(notes: NotesModel? = null) {
        val action = FragmentHomePageDirections
            .actionFragmentHomePageToFragmentEditor(notes)

        navController.navigate(action)
    }

    private fun backPressedListener() {
        val callBack = object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callBack)
    }
}