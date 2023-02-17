package com.francis.note.data

import android.content.ClipData
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import java.util.Date

class NoteViewModel(private val noteDao: NoteDao): ViewModel(){

    val allNotes: LiveData<List<Note>> = noteDao.getNotes().asLiveData()

    private fun insertItem(note: Note){
        // Insert item off the main thread
        viewModelScope.launch {
            noteDao.insert(note)
        }
    }

    // create a new Note object before inserting it into the database
    private fun getNewNoteEntry(title: String, body: String): Note {
        return Note(
            title = title,
            body = body
        )
    }

    // function to be called in the UI
    fun addNewNote(title: String, body: String){
        val newNote = getNewNoteEntry(title, body)
        insertItem(newNote)
    }


    fun isEntryValid(title: String, body: String): Boolean{
        if (title.isBlank() || body.isBlank()){
            return false
        }
        return true
    }
}

class NoteViewModelFactory(private val noteDao: NoteDao): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NoteViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return NoteViewModel(noteDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}