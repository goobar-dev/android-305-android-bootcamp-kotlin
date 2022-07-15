package dev.goobar.androidstudyguide

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
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

    binding.titleEditText.addTextChangedListener(object : TextWatcher {
      override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
      override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

      override fun afterTextChanged(s: Editable) {
        // respond to changes in the title
        binding.titleInputContainer.error = null
        // if both title and content are not empty, show the save button
        if (areInputsEntered()) {
          binding.saveButton.show()
        } else {
          binding.saveButton.hide()
        }
      }
    })

    binding.noteEditText.addTextChangedListener(object : TextWatcher {
      override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
      override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

      override fun afterTextChanged(s: Editable) {
        // respond to changes in the title
        binding.noteInputContainer.error = null
        // if both title and content are not empty, show the save button
        if (areInputsEntered()) {
          binding.saveButton.show()
        } else {
          binding.saveButton.hide()
        }
      }
    })

    binding.saveButton.setOnClickListener {
      if (areInputsEntered()) {
        val snackbar = Snackbar.make(requireView(), "Saved the note!", Snackbar.LENGTH_SHORT)
        snackbar.addCallback(object : BaseTransientBottomBar.BaseCallback<Snackbar>() {
          override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
            super.onDismissed(transientBottomBar, event)
            findNavController().popBackStack()
          }
        })
        snackbar.show()
        return@setOnClickListener
      }
    }

    return binding.root
  }

  private fun areInputsEntered(): Boolean {
    return binding.titleEditText.text.isNullOrBlank().not()
        && binding.noteEditText.text.isNullOrBlank().not()
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