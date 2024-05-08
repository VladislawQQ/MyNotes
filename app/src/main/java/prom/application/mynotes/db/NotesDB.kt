package prom.application.mynotes.db

import androidx.room.Database
import androidx.room.RoomDatabase
import prom.application.mynotes.db.entity.NotesEntity

@Database(
    entities = [NotesEntity::class],
    version = 1,
    exportSchema = false
)
abstract class NotesDB : RoomDatabase() {

    abstract val dao : DAO
}