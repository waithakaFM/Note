package com.francis.note

import com.francis.note.data.NoteRoomDatabase
import android.app.Application

/**
 * In this task you will instantiate the database instance in the Application class
 * To use when creating a ViewModel instance
 */
class NoteApplication: Application() {
    val database: NoteRoomDatabase by lazy {
        NoteRoomDatabase.getDatabase(this)
    }
}