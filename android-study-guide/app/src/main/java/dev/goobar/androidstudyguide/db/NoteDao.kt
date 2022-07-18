package dev.goobar.androidstudyguide.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dev.goobar.androidstudyguide.data.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

  @Query("SELECT * FROM note")
  fun getAll(): Flow<List<Note>>

  @Query("SELECT * FROM note where id=:noteId")
  suspend fun get(noteId: Int): Note

  @Insert
  suspend fun save(note: Note)

  @Update(onConflict = OnConflictStrategy.REPLACE)
  suspend fun update(note: Note)

  @Delete
  suspend fun delete(note: Note)

}