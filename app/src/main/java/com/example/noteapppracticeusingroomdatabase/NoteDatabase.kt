package com.example.noteapppracticeusingroomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun getNoteDao(): NoteDao


    companion object{


    var database: NoteDatabase? = null

    fun getDB(context: Context):NoteDatabase{

        return if (database==null){

            database = Room.databaseBuilder(context,
                NoteDatabase::class.java, "Note-DB")
                .allowMainThreadQueries().build()

            database as NoteDatabase

        } else{
            database as NoteDatabase
        }

    }

    }


}