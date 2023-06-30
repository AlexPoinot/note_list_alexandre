package com.project.note_list_alexandre


import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note :Note)

    @Delete
    suspend fun delete(note: Note)

    @Update
    suspend fun updateCheckedState(note: Note)
    @Query("Select * from notesTable order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>

    @Update
    suspend fun update(note: Note)

}