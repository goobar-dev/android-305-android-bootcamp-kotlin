package dev.goobar.androidstudyguide.studyguide

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dev.goobar.androidstudyguide.R.layout
import dev.goobar.androidstudyguide.databinding.FragmentStudyGuideBinding
import kotlinx.coroutines.launch

/**
 * Will display a list of Android-related study tips/info
 */
class StudyGuideFragment : Fragment() {

  private val viewModel: StudyGuideViewModel by viewModels()

  private var _binding: FragmentStudyGuideBinding? = null
  private val binding: FragmentStudyGuideBinding
    get() = _binding!!

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    _binding = FragmentStudyGuideBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    lifecycleScope.launch {
      repeatOnLifecycle(Lifecycle.State.STARTED) {
        viewModel.state.collect { uiState ->
          binding.titleTextView.text = uiState.title
        }
      }
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}