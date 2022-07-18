package dev.goobar.androidstudyguide.create

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import dev.goobar.androidstudyguide.databinding.FragmentCreateNoteBinding
import dev.goobar.androidstudyguide.datastore.DataStoreCategoryRepository
import dev.goobar.androidstudyguide.datastore.defaultCategoryDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Allows for the saving and editing of a note
 */
class CreateNoteFragment : Fragment() {

  private val categoryRepository = DataStoreCategoryRepository() {
    requireContext()
  }

  private val viewModel: CreateNoteViewModel by viewModels(
    factoryProducer = { CreateNoteViewModelFactory(categoryRepository) }
  )

  private var _binding: FragmentCreateNoteBinding? = null
  private val binding: FragmentCreateNoteBinding
    get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    _binding = FragmentCreateNoteBinding.inflate(inflater, container, false)

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

    binding.categorySpinner.onItemSelectedListener = object : OnItemSelectedListener {
      override fun onNothingSelected(p0: AdapterView<*>?) {}
      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        lifecycleScope.launch(Dispatchers.IO) {
          viewModel.saveSelectedCategory(position)
        }
      }
    }

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    lifecycleScope.launch {
      repeatOnLifecycle(Lifecycle.State.STARTED) {
        viewModel.state.collect { uiState ->
          binding.categorySpinner.adapter = CategorySpinnerAdapter(requireContext(), uiState.categories)
        }
      }
    }

    lifecycleScope.launch {
      repeatOnLifecycle(Lifecycle.State.STARTED) {
        categoryRepository.defaultCategory.collect { category ->
          binding.categorySpinner.setSelection(viewModel.indexForCategory(category.category))
        }
      }
    }
  }

  private fun areInputsEntered(): Boolean {
    return binding.titleEditText.text.isNullOrBlank().not()
        && binding.noteEditText.text.isNullOrBlank().not()
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
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