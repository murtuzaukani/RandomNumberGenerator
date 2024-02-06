package com.murtuza4u.randomnumbergenerator

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.murtuza4u.randomnumbergenerator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var clickCount = 0
    private val lastThreeRandomNumbers = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val buttons = arrayOf(
            binding.button, binding.button2, binding.button3,
            binding.button4, binding.button5, binding.button6,
            binding.button7, binding.button8, binding.button9
        )

        buttons.forEach { button ->
            button.setOnClickListener {
                handleButtonClick()
            }
        }
    }

    private fun handleButtonClick() {
        if (clickCount < 3) {
            generateAndSetRandomNumber()
            clickCount++
        } else {
            showPopup()
        }
    }

    private fun generateAndSetRandomNumber() {
        val random = (1..5).random()
        binding.textView.text = getString(R.string.random_number, random)
        lastThreeRandomNumbers.add(random)
    }

    private fun showPopup() {
        val lastThreeNumbersText = lastThreeRandomNumbers.joinToString(", ")
        AlertDialog.Builder(this).apply {
            setTitle("Maximum Clicks Reached")
            setMessage("You have reached the maximum clicks allowed.\n\nLast Three Numbers: $lastThreeNumbersText")
            setPositiveButton("Clear") { dialog, _ ->
                dialog.dismiss()
                resetClickCountAndClearNumbers()
            }
        }.create().show()
    }

    private fun resetClickCountAndClearNumbers() {
        clickCount = 0
        lastThreeRandomNumbers.clear()
        binding.textView.text = " "
    }
}
