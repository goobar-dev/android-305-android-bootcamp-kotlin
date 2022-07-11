package dev.goobar.gamecounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import dev.goobar.gamecounter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  private val targetPoints = 20
  private var currentPoints = 0

  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.targetPoints.text = "$targetPoints"
    binding.currentPoints.text = "$currentPoints"

    binding.resetButton.setOnClickListener {
      currentPoints = 0
      updateScoreText()

      binding.plusOneButton.visibility = View.VISIBLE
      binding.resetButton.visibility = View.GONE
    }

    binding.plusOneButton.setOnClickListener {
      currentPoints++
      updateScoreText()

      if (currentPoints == targetPoints) {
        Toast.makeText(this@MainActivity, "You Won!", Toast.LENGTH_SHORT).show()

        binding.plusOneButton.visibility = View.GONE
        binding.resetButton.visibility = View.VISIBLE
      }
    }
  }

  private fun updateScoreText() {
    binding.currentPoints.text = "$currentPoints"
  }
}