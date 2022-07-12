package dev.goobar.androidstudyguide

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import dev.goobar.androidstudyguide.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.showTwitterButton.setOnClickListener {
      val showTwitterIntent = Intent(this@MainActivity, TwitterActivity::class.java)
      startActivity(showTwitterIntent)
    }

    binding.createNoteButton.setOnClickListener {
      showFragment(CreateNoteFragment(), "Create Note")
    }

    binding.noteDetailButton.setOnClickListener {
      showFragment(NoteDetailFragment(), "Note Details")
    }

    binding.myNotesButton.setOnClickListener {
      showFragment(MyNotesFragment(), "My Notes")
    }

    binding.studyGuideButton.setOnClickListener {
      showFragment(StudyGuideFragment(), "Study Guide")
    }
  }

  private fun showFragment(fragment: Fragment, name: String) {
    supportFragmentManager
      .beginTransaction()
      .replace(R.id.fragmentContainer, fragment)
      .addToBackStack(name)
      .commit()
  }
}