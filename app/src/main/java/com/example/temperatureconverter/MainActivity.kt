package com.example.temperatureconverter

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var temperatureEditText: EditText
    private lateinit var conversionRadioGroup: RadioGroup
    private lateinit var convertButton: Button
    private lateinit var convertedTemperatureTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        temperatureEditText = findViewById(R.id.temperatureEditText)
        conversionRadioGroup = findViewById(R.id.conversionRadioGroup)
        convertButton = findViewById(R.id.convertButton)
        convertedTemperatureTextView = findViewById(R.id.convertedTemperatureTextView)

        convertButton.setOnClickListener {
            convertTemperature()
        }
    }

    private fun convertTemperature() {
        val temperature = temperatureEditText.text.toString().toDoubleOrNull()
        val selectedConversionId = conversionRadioGroup.checkedRadioButtonId
        val selectedConversionRadioButton = findViewById<RadioButton>(selectedConversionId)

        if (temperature != null && selectedConversionRadioButton != null) {
            val convertedTemperature = when (selectedConversionRadioButton.id) {
                R.id.celsiusToFahrenheitRadioButton -> celsiusToFahrenheit(temperature)
                R.id.fahrenheitToCelsiusRadioButton -> fahrenheitToCelsius(temperature)
                R.id.celsiusToKelvinRadioButton -> celsiusToKelvin(temperature)
                R.id.kelvinToCelsiusRadioButton -> kelvinToCelsius(temperature)
                R.id.fahrenheitToKelvinRadioButton -> fahrenheitToKelvin(temperature)
                R.id.kelvinToFahrenheitRadioButton -> kelvinToFahrenheit(temperature)
                else -> {
                    null
                }
            }

            if (convertedTemperature != null) {
                convertedTemperatureTextView.text = "Converted temperature: $convertedTemperature"
                convertedTemperatureTextView.visibility = View.VISIBLE
            } else {
                convertedTemperatureTextView.text = "Invalid conversion"
                convertedTemperatureTextView.visibility = View.VISIBLE
            }
        } else {
            convertedTemperatureTextView.text = "Invalid input"
            convertedTemperatureTextView.visibility = View.VISIBLE
        }
    }

    private fun celsiusToFahrenheit(celsius: Double): Double {
        return celsius * 9 / 5 + 32
    }

    private fun fahrenheitToCelsius(fahrenheit: Double): Double {
        return (fahrenheit - 32) * 5 / 9
    }

    private fun celsiusToKelvin(celsius: Double): Double {
        return celsius + 273.15
    }

    private fun kelvinToCelsius(kelvin: Double): Double {
        return kelvin - 273.15
    }

    private fun fahrenheitToKelvin(fahrenheit: Double): Double {
        return (fahrenheit - 32) * 5 / 9 + 273.15
    }

    private fun kelvinToFahrenheit(kelvin: Double): Double {
        return kelvin * 9 / 5 - 459.67
    }
}
