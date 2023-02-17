package com.francis.note


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.francis.note.data.Note
import com.francis.note.data.NoteViewModel
import com.francis.note.data.NoteViewModelFactory
import com.francis.note.databinding.FragmentNewNoteBinding

class NewNoteFragment : Fragment() {

    // create a ViewModel instance using a ViewModel factory.
    private val viewModel: NoteViewModel by activityViewModels {
        NoteViewModelFactory(
            (activity?.application as NoteApplication).database
                .noteDao()
        )
    }

    lateinit var note: Note

    private var _binding: FragmentNewNoteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentNewNoteBinding.inflate(inflater, container, false)
        return binding.root


    }

    private fun isEntryValid(): Boolean{
        return viewModel.isEntryValid(
            binding.etTitle.text.toString(),
            binding.etBody.text.toString()
        )
    }

    private fun addNewNote(){
        if (isEntryValid()){
           viewModel.addNewNote(
               binding.etTitle.text.toString(),
               binding.etBody.text.toString()
           )
            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
        }else
            Toast.makeText(context, "Empty Note Discarded", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        addNewNote()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}