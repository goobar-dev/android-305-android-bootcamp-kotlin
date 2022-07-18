package dev.goobar.androidstudyguide.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import dev.goobar.androidstudyguide.R.layout
import dev.goobar.androidstudyguide.databinding.FragmentNoteDetailsBinding
import dev.goobar.androidstudyguide.studyGuideApplication
import kotlinx.coroutines.launch

/**
 * Displays the details of a saved note
 */
class NoteDetailsFragment : Fragment() {

  private val args: NoteDetailsFragmentArgs by navArgs()
  private val viewModel: NoteDetailsViewModel by viewModels(
    factoryProducer = {
      NoteDetailsViewModelFactory(
        args.selectedNoteId,
        requireActivity().studyGuideApplication().database.noteDao()
      )
    }
  )

  /**
   * The 2 variables here allow us to set the binding to null in onDestroyView(), while
   * continuing to access the binding in a non-null way within the expected, valid lifecycle of
   * the fragment.
   */
  private var _binding: FragmentNoteDetailsBinding? = null
  private val binding: FragmentNoteDetailsBinding
    get() = _binding!!

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    _binding = FragmentNoteDetailsBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    lifecycleScope.launch {
      repeatOnLifecycle(Lifecycle.State.STARTED) {
        viewModel.state.collect { uiState ->
          binding.noteTitleTextView.text = uiState.title
          binding.noteCategoryTextView.text = uiState.category
          binding.noteContentTextView.text = uiState.content
        }
      }
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}