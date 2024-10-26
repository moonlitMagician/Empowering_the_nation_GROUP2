package com.example.semester2

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.ArrayList

class monthcourses : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private val selectedCourses = mutableListOf<String>()
    private lateinit var firstAidButton: Button
    private lateinit var landButton: Button
    private lateinit var sewButton: Button
    private lateinit var lifeSkillsButton: Button
    private var totalPrice = 0.0

    private val coursePrices = mapOf(
        "First Aid" to 1500.0,
        "Landscaping" to 1500.0,
        "Sewing" to 1500.0,
        "Life Skills" to 1500.0
    )

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_monthcourses)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.monthCourses)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        sharedPreferences = getSharedPreferences("CartPrefs", MODE_PRIVATE)

        val homeButton = findViewById<ImageButton>(R.id.courseHomeButton)
        val aboutButton = findViewById<ImageButton>(R.id.courseAboutUsButton)
        val checkoutButton = findViewById<ImageButton>(R.id.cartButton)

        firstAidButton = findViewById<Button>(R.id.firstAidButton)
        landButton = findViewById<Button>(R.id.landButton)
        sewButton = findViewById<Button>(R.id.sewButton)
        lifeSkillsButton = findViewById<Button>(R.id.lifeSkillsButton)

        homeButton.setOnClickListener {
            val intent = Intent(this, homePage::class.java)
            startActivity(intent)
        }

        aboutButton.setOnClickListener {
            val intent = Intent(this, aboutUsPage::class.java)
            startActivity(intent)
        }

        restoreButtonStates()

        firstAidButton.setOnClickListener {
            onAddToCartClicked("firstAidButton")
            addCourse("firstAid", firstAidButton)
        }

        landButton.setOnClickListener {
            onAddToCartClicked("landButton")
            addCourse("Landscaping", landButton)
        }

        sewButton.setOnClickListener {
            onAddToCartClicked("sewButton")
            addCourse("Sewing", sewButton)
        }

        lifeSkillsButton.setOnClickListener {
            onAddToCartClicked("lifeSkillsButton")
            addCourse("Life Skills", lifeSkillsButton)
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
            "firstAidButton" -> {
                firstAidButton.isEnabled = false
                firstAidButton.text = "Added to Cart"
            }
            "landButton" -> {
                landButton.isEnabled = false
                landButton.text = "Added to Cart"
            }
            "sewButton" -> {
                sewButton.isEnabled = false
                sewButton.text = "Added to Cart"
            }
            "lifeSkillsButton" -> {
                lifeSkillsButton.isEnabled = false
                lifeSkillsButton.text = "Added to Cart"
            }
        }
        // Save the state
        saveButtonState(buttonKey)
    }

    private fun restoreButtonStates() {
        val sharedPref = getSharedPreferences("buttonPrefs", Context.MODE_PRIVATE)

        if (sharedPref.getBoolean("firstAidButton", false)) {
            firstAidButton.isEnabled = false
            firstAidButton.text = "Added to Cart"
        }
        if (sharedPref.getBoolean("landButton", false)) {
            landButton.isEnabled = false
            landButton.text = "Added to Cart"
        }
        if (sharedPref.getBoolean("sewButton", false)) {
            sewButton.isEnabled = false
            sewButton.text = "Added to Cart"
        }
        if (sharedPref.getBoolean("lifeSkillsButton", false)) {
            lifeSkillsButton.isEnabled = false
            lifeSkillsButton.text = "Added to Cart"
        }
    }

    private fun addCourse(courseName: String, button: Button) {
        selectedCourses.add(courseName)
        totalPrice += coursePrices[courseName] ?: 0.0
        button.isEnabled = false
        button.text = "Added to Cart"
    }


}