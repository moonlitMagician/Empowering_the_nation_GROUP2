package com.example.semester2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class homePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.homePage)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val aboutUsButton = findViewById<ImageButton>(R.id.aboutUsButton)
        val homeButton = findViewById<ImageButton>(R.id.homeButton)
        val sixWeekButton = findViewById<Button>(R.id.sixWeekButtonInfo)
        val sixMonthButton = findViewById<Button>(R.id.sixMonthButtonInfo)
        val checkoutButton = findViewById<ImageButton>(R.id.cartButton)
        aboutUsButton.setOnClickListener {
           val intent = Intent(this, aboutUsPage::class.java)
           startActivity(intent)
        }

        sixWeekButton.setOnClickListener {
          val intent = Intent(this, coursesPage::class.java)
           startActivity(intent)
         }

        checkoutButton.setOnClickListener {
          val intent = Intent(this, checkout::class.java)
          startActivity(intent)
        }

        sixMonthButton.setOnClickListener {
            val intent = Intent(this, monthcourses::class.java)
            startActivity(intent)
        }



    }
}