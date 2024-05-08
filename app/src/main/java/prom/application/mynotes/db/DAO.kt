package prom.application.mynotes.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import prom.application.mynotes.db.entity.NotesEntity

@Dao
interface DAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(note: NotesEntity)

    @Query("SELECT * FROM notes ORDER BY id DESC")
    suspend fun getNotes() : List<NotesEntity>

    @Query("SELECT * FROM notes WHERE notes.title LIKE '%' || :query || '%'")
    suspend fun findNotesWith(query: String) : List<NotesEntity>

    @Query("UPDATE notes SET title = :title, notes = :notes WHERE id = :id")
    suspend fun updateNotes(id : Int, title : String, notes : String)

    @Query("UPDATE notes SET isSelected = :isSelected WHERE id = :id")
    suspend fun toggle(id : Int, isSelected : Boolean)

    @Delete
    suspend fun deleteNotes(notes: NotesEntity)
}