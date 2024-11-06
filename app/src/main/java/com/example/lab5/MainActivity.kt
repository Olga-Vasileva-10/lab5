package com.example.currencyconverter

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var priceInput: EditText
    private lateinit var currencyGroup: RadioGroup
    private lateinit var calculateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        priceInput = findViewById(R.id.editTextNumber)
        currencyGroup = findViewById(R.id.currencyGroup)
        calculateButton = findViewById(R.id.button)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        calculateButton.setOnClickListener {
            calculateResult()
        }
    }

    private fun calculateResult() {
        val price = priceInput.text.toString().toDoubleOrNull()
        if (price == null) {
            Toast.makeText(this, "Введите корректную цену", Toast.LENGTH_SHORT).show()
            return
        }

        val selectedCurrencyId = currencyGroup.checkedRadioButtonId
        if (selectedCurrencyId == -1) {
            Toast.makeText(this, "Выберите валюту", Toast.LENGTH_SHORT).show()
            return
        }

        val conversionRate = when (selectedCurrencyId) {
            R.id.dollars -> 97.58
            R.id.euro -> 106.18
            R.id.funt -> 126.14
            else -> 0.0
        }

        val result = price * conversionRate
        val intent = Intent(this, ResultActivity::class.java).apply {
            putExtra("RESULT", result)
        }
        startActivity(intent)
    }
}
