package com.example.noteapppracticeusingroomdatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {

    @Insert
    fun insertNote(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("SELECT * FROM Note")
    fun getAllNote(): List<Note>

    @Query("Select * From Note Where noteID IN(:id)")
    fun getNoteById(id : List<Int>): List<Note>


}