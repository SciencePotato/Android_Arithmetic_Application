package com.example.android_arithmetic_application

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Button


class MainActivity : AppCompatActivity() {
    private lateinit var spinner: Spinner
    private lateinit var number1: EditText
    private lateinit var number2: EditText
    private lateinit var label: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        spinner = findViewById(R.id.spinner)
        number1 = findViewById(R.id.FirstNum)
        number2 = findViewById(R.id.SecondNum)
        label = findViewById(R.id.textView)

        ArrayAdapter.createFromResource(
            this,
            R.array.operand,
            android.R.layout.simple_spinner_item
        ).also {
            adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
        }

        val calculateButton = findViewById<Button>(R.id.calculateButton)
        calculateButton.setOnClickListener {
            calculate()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun calculate() {
        val operand1Str = number1.text.toString()
        val operand2Str = number2.text.toString()

        if (operand1Str.isEmpty() || operand2Str.isEmpty()) {
            showError("Please enter valid numbers.")
            return
        }

        val operand1 = operand1Str.toDoubleOrNull()
        val operand2 = operand2Str.toDoubleOrNull()

        if (operand1 == null || operand2 == null) {
            showError("Invalid operands. Please enter valid numbers.")
            return
        }

        val Operation = spinner.selectedItem.toString()
        val result = when (Operation) {
            "Addition" -> operand1 + operand2
            "Subtraction" -> operand1 - operand2
            "Multiplication" -> operand1 * operand2
            "Division" -> {
                if (operand2 == 0.0) {
                    showError("Dividing by Zero is not allowed.")
                    return
                }
                operand1 / operand2
            }
            "Modulus" -> {
                if (operand2 == 0.0) {
                    showError("Modulus by Zero is not allowed.")
                    return
                }
                operand1 % operand2
            }
            else -> {
                showError("This operation is invalid.")
                return
            }
        }

        label.text = "Result: $result"
    }

    @SuppressLint("SetTextI18n")
    private fun showError(message: String) {
        label.text = "Error: $message"
    }
}