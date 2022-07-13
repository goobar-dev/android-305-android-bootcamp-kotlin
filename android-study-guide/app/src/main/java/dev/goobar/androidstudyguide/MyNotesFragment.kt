package dev.goobar.androidstudyguide

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dev.goobar.androidstudyguide.databinding.FragmentMyNotesBinding

class MyNotesFragment : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val binding = FragmentMyNotesBinding.inflate(inflater, container, false)

    binding.createNoteButton.setOnClickListener {
      findNavController().navigate(R.id.action_myNotesFragment_to_createNoteFragment)
    }

    binding.showDetailsButton.setOnClickListener {
      findNavController().navigate(R.id.action_myNotesFragment_to_noteDetailFragment)
    }

    return binding.root
  }
}