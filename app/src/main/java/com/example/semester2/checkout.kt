package com.example.semester2

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class checkout : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_checkout)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.checkout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val selectedCourses = intent.getStringArrayListExtra("selectedCourses") ?: arrayListOf()
        val courseTextView = findViewById<TextView>(R.id.orderContents)
        val totalPriceText = findViewById<TextView>(R.id.totalPriceText)
        val discountContents = findViewById<TextView>(R.id.discountContents)

        val totalPrice = intent.getDoubleExtra("totalPrice", 0.0)
        var discountAmount = 0.0
        var finalPrice = 0.0

        var discountText = ""
        var discountPercent = 0

        if (selectedCourses.size <= 1) {
            discountText = "One Course Selected - 0% discount"
            discountPercent = 0
        } else if (selectedCourses.size == 2){
            discountText = "Two Courses Selected - 5% discount"
            discountPercent = 5
        } else if (selectedCourses.size == 3){
            discountText = "Three Courses Selected - 10% discount"
            discountPercent = 10
        } else if (selectedCourses.size > 3) {
            discountText = "More than three courses selected - 15% discount"
            discountPercent = 15
        }

        discountAmount = (totalPrice * discountPercent)/100
        finalPrice = totalPrice - discountAmount

        courseTextView.text = selectedCourses?.joinToString("\n") ?: "No courses selected"
        discountContents.text = "Discount:\n $discountText"
        totalPriceText.text = "R: $finalPrice"
    }
}