package prom.application.mynotes.di

import android.app.Application
import androidx.room.Room
import prom.application.mynotes.db.DAO
import prom.application.mynotes.db.NotesDB
import prom.application.mynotes.db.NotesDBImpl
import prom.application.mynotes.db.NotesRep
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun providePokemonRepository(
        db: NotesDB,
    ): NotesRep {
        return NotesDBImpl(
            db.dao
        )
    }

    @Provides
    @Singleton
    fun providePokemonLocalDatabase(app: Application): NotesDB {
        return Room
            .databaseBuilder(
                app,
                NotesDB::class.java,
                "local_notes"
            )
            .build()
    }


    @Provides
    @Singleton
    fun provideNotesDAO(database: NotesDB): DAO {
        return database.dao
    }
}