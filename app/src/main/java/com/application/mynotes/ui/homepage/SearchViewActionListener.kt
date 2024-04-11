package com.application.mynotes.ui.homepage

interface SearchViewActionListener {

    fun onTextChange(newText : String)
    fun onTextSubmit(query : String)
    fun onClickListener()
    fun getNotesList()

}