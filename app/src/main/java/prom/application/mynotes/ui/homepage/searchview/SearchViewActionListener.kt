package prom.application.mynotes.ui.homepage.searchview

interface SearchViewActionListener {

    fun onTextChange(newText : String)
    fun onTextSubmit(query : String)
    fun onClickListener()
    fun getNotesList()

}