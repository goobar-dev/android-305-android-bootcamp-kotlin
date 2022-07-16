package dev.goobar.androidstudyguide.mynotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dev.goobar.androidstudyguide.R
import dev.goobar.androidstudyguide.databinding.FragmentMyNotesBinding
import dev.goobar.androidstudyguide.databinding.FragmentNoteDetailBinding

/**
 * Displays a list of saved notes
 */
class MyNotesFragment : Fragment() {

  private var _binding: FragmentMyNotesBinding? = null
  private val binding: FragmentMyNotesBinding
    get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentMyNotesBinding.inflate(inflater, container, false)

    binding.createNoteButton.setOnClickListener {
      findNavController().navigate(R.id.action_myNotesFragment_to_createNoteFragment)
    }

    binding.showDetailsButton.setOnClickListener {
      findNavController().navigate(R.id.action_myNotesFragment_to_noteDetailFragment)
    }

    return binding.root
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}