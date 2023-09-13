package com.example.android_arithmetic_application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView

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
    }
}