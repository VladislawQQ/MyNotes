package com.application.mynotes.ui.homepage

import android.animation.Animator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.FrameLayout
import android.widget.SearchView
import com.application.mynotes.databinding.SearchViewBinding


class SearchView(context: Context, attrs: AttributeSet)
    : FrameLayout(context, attrs) {

    private var binding: SearchViewBinding = SearchViewBinding.inflate(LayoutInflater.from(context), this, true)

    private var listener: SearchViewActionListener? = null

    fun setCustomViewListener(customViewListener: SearchViewActionListener) {
        listener = customViewListener
    }

    init {
        binding.openSearchButton.setOnClickListener {
            listener?.onClickListener()
            openSearch()
        }
        binding.searchViewSearchNotes.setOnQueryTextFocusChangeListener { _, isFocus ->
            if (!isFocus) closeSearch()
        }
        binding.searchViewSearchNotes.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                listener?.onTextChange(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                listener?.onTextSubmit(query)
                return false
            }

        })
    }

    private fun openSearch() {
        with(binding) {
            searchOpenView.visibility = View.VISIBLE
            searchClosedView.visibility = View.INVISIBLE
            val circularReveal = ViewAnimationUtils.createCircularReveal(
                searchOpenView,
                (openSearchButton.right + openSearchButton.left) / 2,
                (openSearchButton.top + openSearchButton.bottom) / 2,
                0f, width.toFloat()
            )
            circularReveal.duration = 300
            circularReveal.start()
        }
    }

    private fun closeSearch() {
        listener?.getNotesList()
        listener?.onClickListener()
        with(binding) {
            val circularConceal = ViewAnimationUtils.createCircularReveal(
                searchOpenView,
                (openSearchButton.right + openSearchButton.left) / 2,
                (openSearchButton.top + openSearchButton.bottom) / 2,
                width.toFloat(), 0f
            )

            circularConceal.duration = 300
            circularConceal.start()
            circularConceal.addListener(object : Animator.AnimatorListener {

                override fun onAnimationStart(p0: Animator) = Unit
                override fun onAnimationCancel(p0: Animator) = Unit
                override fun onAnimationRepeat(p0: Animator) = Unit

                override fun onAnimationEnd(p0: Animator) {
                    with(binding) {
                        searchOpenView.visibility = View.INVISIBLE
                        searchClosedView.visibility = View.VISIBLE
                        circularConceal.removeAllListeners()
                    }
                }
            })
        }
    }

}