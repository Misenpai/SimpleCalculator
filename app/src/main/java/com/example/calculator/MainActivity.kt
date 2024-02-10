package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val resultTextView: TextView = findViewById(R.id.result)
        resultTextView.visibility = View.INVISIBLE

        // Set OnClickListener for the equals button
        val equalsButton: Button = findViewById(R.id.btn_equal)
        equalsButton.setOnClickListener {
            calculateResult()
        }

        // Call the function to set OnClickListener for other buttons
        setButtonClickListener()
    }

    private fun setButtonClickListener() {
        // Find all the buttons except the equals button
        val buttons = arrayOf(
            findViewById<Button>(R.id.btn_c),
            findViewById<Button>(R.id.btn_plus_and_minus),
            findViewById<Button>(R.id.btn_mod),
            findViewById<Button>(R.id.btn_div),
            findViewById<Button>(R.id.btn_7),
            findViewById<Button>(R.id.btn_8),
            findViewById<Button>(R.id.btn_9),
            findViewById<Button>(R.id.btn_mul),
            findViewById<Button>(R.id.btn_minus),
            findViewById<Button>(R.id.btn_4),
            findViewById<Button>(R.id.btn_5),
            findViewById<Button>(R.id.btn_6),
            findViewById<Button>(R.id.btn_3),
            findViewById<Button>(R.id.btn_2),
            findViewById<Button>(R.id.btn_1),
            findViewById<Button>(R.id.btn_plus),
            findViewById<Button>(R.id.btn_0),
            findViewById<Button>(R.id.btn_dot)
        )

        // Set OnClickListener for each button
        for (button in buttons) {
            button.setOnClickListener {
                if (button.id == R.id.btn_c) {
                    clearCalculationView()
                } else {
                    appendToCalculationView(button.text.toString())
                }
            }
        }
    }

    private fun clearCalculationView() {
        // Clear the calculation view and hide the result
        val calculationTextView: TextView = findViewById(R.id.calculation_view)
        val resultTextView: TextView = findViewById(R.id.result)

        calculationTextView.text = "0"
        resultTextView.visibility = View.INVISIBLE
    }


    private fun appendToCalculationView(text: String) {
        // Append the clicked button's text to calculation_view
        val calculationTextView: TextView = findViewById(R.id.calculation_view)

        // Check if the calculation view contains only a zero
        if (calculationTextView.text.toString() == "0") {
            // If calculation view contains only zero, replace it with the new text
            calculationTextView.text = text
        } else {
            // Otherwise, append the new text to the existing text
            calculationTextView.append(text)
        }
    }

    private fun calculateResult() {
        // Get the calculation string from calculation_view
        val calculationTextView: TextView = findViewById(R.id.calculation_view)
        val calculationString = calculationTextView.text.toString()

        // Perform the calculation
        val resultTextView: TextView = findViewById(R.id.result)
        try {
            val expression = ExpressionBuilder(calculationString).build()
            val result = expression.evaluate()
            resultTextView.text = result.toString()
            resultTextView.visibility = View.VISIBLE
        } catch (e: Exception) {
            // Handle invalid calculation
            resultTextView.text = "Invalid Calculation"
            resultTextView.visibility = View.VISIBLE
        }
    }
}
