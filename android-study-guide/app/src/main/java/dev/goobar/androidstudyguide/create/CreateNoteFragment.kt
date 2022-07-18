package dev.goobar.androidstudyguide.create

import android.app.Activity
import android.content.Context
import android.content.Intent
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
import dev.goobar.androidstudyguide.studyGuideApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private val REQUEST_CHOOSE_IMAGE = 0

/**
 * Allows for the saving and editing of a note
 */
class CreateNoteFragment : Fragment() {

  private val categoryRepository = DataStoreCategoryRepository() {
    requireContext()
  }

  private val viewModel: CreateNoteViewModel by viewModels(
    factoryProducer = {
      CreateNoteViewModelFactory(
        categoryRepository,
        requireActivity().studyGuideApplication().database.noteDao()
      )
    }
  )

  private var noteId: Int? = null
  private var uriToImage: String? = null

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
        viewModel.save(
          title = binding.titleEditText.text.toString(),
          categoryIndex = binding.categorySpinner.selectedItemPosition,
          content = binding.noteEditText.text.toString(),
          imageUri = uriToImage
        )
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

    binding.imageView?.setOnClickListener {
      selectImage()
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

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if(requestCode != REQUEST_CHOOSE_IMAGE || resultCode == Activity.RESULT_CANCELED) return

    if (resultCode == Activity.RESULT_OK && data != null) {
      val contentResolver = requireContext().contentResolver
      val takeFlags: Int = Intent.FLAG_GRANT_READ_URI_PERMISSION
      contentResolver.takePersistableUriPermission(data.data!!, takeFlags)

      uriToImage = data.data.toString()
      binding.imageView?.setImageURI(data.data)
    }
  }

  private fun selectImage() {
    val intent = Intent().apply {
      type = "image/*"
      action = Intent.ACTION_OPEN_DOCUMENT
    }
    startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_CHOOSE_IMAGE)
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