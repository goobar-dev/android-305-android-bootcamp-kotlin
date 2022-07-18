package dev.goobar.androidstudyguide.mynotes

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.goobar.androidstudyguide.R
import dev.goobar.androidstudyguide.data.Note
import dev.goobar.androidstudyguide.databinding.ItemNoteBinding

class NoteViewHolder(val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {

  fun bindNode(note: Note) {
    binding.titleTextView.text = note.title
    binding.categoryText.text = note.category
    binding.contentText.text = note.content
    if (note.imageUri != null) {
      binding.noteImage.setImageURI(Uri.parse(note.imageUri))
    }
  }
}

object NoteDiffUtil : DiffUtil.ItemCallback<Note>() {
  override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
    return oldItem == newItem
  }

  override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
    return oldItem == newItem
  }
}

class MyNotesListAdapter(private val noteClickHandler: (Note) -> Unit) : ListAdapter<Note, NoteViewHolder>(NoteDiffUtil) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
    val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return NoteViewHolder(binding)
  }

  override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
    val note = getItem(position)
    holder.bindNode(note)
    holder.itemView.setOnClickListener { noteClickHandler(note) }
  }
}