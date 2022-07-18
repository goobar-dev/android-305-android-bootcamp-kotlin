package dev.goobar.androidstudyguide.db

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.goobar.androidstudyguide.data.Note

@Database(entities = [Note::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
  abstract fun noteDao(): NoteDao
}