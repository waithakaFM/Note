package com.francis.note

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.francis.note.data.Note

/**
 * [ListAdapter] implementation for the recyclerview.
 */

class NoteListAdapter(private val onItemClicked: (Note) -> Unit) :
    ListAdapter<Note, NoteListAdapter.ItemViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener{
            onItemClicked(current)
        }
        holder.bind(current)
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textTitle: TextView = itemView.findViewById(R.id.textTitle)
        private val textBody: TextView = itemView.findViewById(R.id.textBody)

        fun bind(note: Note) {
            textTitle.text = note.title
            textBody.text = note.body
        }
    }

    // the ListAdapter will use this to figure out what changed in the list.
    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.title == newItem.title
            }
        }
    }
}