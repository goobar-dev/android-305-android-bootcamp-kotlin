package dev.goobar.androidstudyguide

import android.content.Context
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.google.android.material.internal.TextWatcherAdapter
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import dev.goobar.androidstudyguide.databinding.FragmentCreateNoteBinding

private val CATEGORIES = listOf("Tooling", "Kotlin", "UI", "Navigation", "Misc")

class CreateNoteFragment : Fragment() {

  private lateinit var binding: FragmentCreateNoteBinding

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = FragmentCreateNoteBinding.inflate(inflater, container, false)

    binding.categorySpinner.adapter = CategorySpinnerAdapter(requireContext(), CATEGORIES)

    return binding.root
  }
}

class CategorySpinnerAdapter(
  context: Context, items: List<String>) :
  ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item
  ) {
  init {
    addAll(items)
  }
}