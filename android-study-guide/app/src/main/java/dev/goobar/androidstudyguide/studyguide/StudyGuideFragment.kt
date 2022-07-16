package dev.goobar.androidstudyguide.studyguide

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.goobar.androidstudyguide.R.layout
import dev.goobar.androidstudyguide.databinding.FragmentStudyGuideBinding

/**
 * Will display a list of Android-related study tips/info
 */
class StudyGuideFragment : Fragment() {

  private var _binding: FragmentStudyGuideBinding? = null
  private val binding: FragmentStudyGuideBinding
    get() = _binding!!

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    _binding = FragmentStudyGuideBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}