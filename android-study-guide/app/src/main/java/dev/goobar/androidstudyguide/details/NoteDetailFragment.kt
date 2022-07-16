package dev.goobar.androidstudyguide.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.goobar.androidstudyguide.R.layout
import dev.goobar.androidstudyguide.databinding.FragmentNoteDetailBinding

/**
 * Displays the details of a saved note
 */
class NoteDetailFragment : Fragment() {

  /**
   * The 2 variables here allow us to set the binding to null in onDestroyView(), while
   * continuing to access the binding in a non-null way within the expected, valid lifecycle of
   * the fragment.
   */
  private var _binding: FragmentNoteDetailBinding? = null
  private val binding: FragmentNoteDetailBinding
    get() = _binding!!

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    _binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}