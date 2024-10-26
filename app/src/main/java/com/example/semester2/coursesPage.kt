package com.example.semester2

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.Image
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.ArrayList

class coursesPage : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private val selectedCourses = mutableListOf<String>()
    private lateinit var childButton: Button
    private lateinit var gardenButton: Button
    private lateinit var cookingButton: Button
    private var totalPrice = 0.0

    private val coursePrices = mapOf(
        "Child Minding" to 750.0,
        "Garden Maintenance" to 750.0,
        "Cooking" to 750.0
    )

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.weekcourses)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.courseWeek)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        sharedPreferences = getSharedPreferences("CartPrefs", MODE_PRIVATE)

        val homeButton = findViewById<ImageButton>(R.id.courseHomeButton)
        val aboutButton = findViewById<ImageButton>(R.id.courseAboutUsButton)
        val checkoutButton = findViewById<ImageButton>(R.id.cartButton)

        childButton = findViewById<Button>(R.id.childButton)
        gardenButton = findViewById<Button>(R.id.gardenButton)
        cookingButton = findViewById<Button>(R.id.cookingButton)

        homeButton.setOnClickListener {
            val intent = Intent(this, homePage::class.java)
            startActivity(intent)
        }

        aboutButton.setOnClickListener {
            val intent = Intent(this, aboutUsPage::class.java)
            startActivity(intent)
        }

        restoreButtonStates()

        childButton.setOnClickListener {
            onAddToCartClicked("childButton")
            addCourse("Child Minding", childButton)
        }

        gardenButton.setOnClickListener {
            onAddToCartClicked("gardenButton")
            addCourse("Garden Maintenance", gardenButton)
        }

        cookingButton.setOnClickListener {
            onAddToCartClicked("cookingButton")
            addCourse("Cooking", cookingButton)
        }

        checkoutButton.setOnClickListener {
            val intent = Intent(this, checkout::class.java)
            intent.putStringArrayListExtra("selectedCourses", ArrayList(selectedCourses))
            intent.putExtra("totalPrice", totalPrice)
            startActivity(intent)
        }
    }

    private fun saveButtonState(buttonKey: String) {
        val sharedPref = getSharedPreferences("buttonPrefs", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putBoolean(buttonKey, true)
            apply()
        }
    }

    private fun onAddToCartClicked(buttonKey: String) {
        // Disable the button and update text
        when (buttonKey) {
            "childButton" -> {
                childButton.isEnabled = false
                childButton.text = "Added to Cart"
            }
            "gardenButton" -> {
                gardenButton.isEnabled = false
                gardenButton.text = "Added to Cart"
            }
            "cookingButton" -> {
                cookingButton.isEnabled = false
                cookingButton.text = "Added to Cart"
            }
        }
        // Save the state
        saveButtonState(buttonKey)
    }

    private fun restoreButtonStates() {
        val sharedPref = getSharedPreferences("buttonPrefs", Context.MODE_PRIVATE)

        if (sharedPref.getBoolean("childButton", false)) {
            childButton.isEnabled = false
            childButton.text = "Added to Cart"
        }
        if (sharedPref.getBoolean("gardenButton", false)) {
            gardenButton.isEnabled = false
            gardenButton.text = "Added to Cart"
        }
        if (sharedPref.getBoolean("cookingButton", false)) {
            cookingButton.isEnabled = false
            cookingButton.text = "Added to Cart"
        }
    }

    private fun addCourse(courseName: String, button: Button) {
        selectedCourses.add(courseName)
        totalPrice += coursePrices[courseName] ?: 0.0
        button.isEnabled = false
        button.text = "Added to Cart"
    }


}